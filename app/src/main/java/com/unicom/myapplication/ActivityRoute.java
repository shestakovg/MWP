package com.unicom.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.PopupMenu;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Entitys.OutletObject;
import core.appManager;
import db.DbOpenHelper;

public class ActivityRoute extends ActionBarActivity {

    private Spinner spinner;
    private ListView listRoute;
    private  SimpleAdapter sa;
    private List<OutletObject> outletsObjectList;
    private String[] outletsId;
    private OutletObject selectedOutlet;
    private String routeWhere = "";
    private String[] daysString = {"��� �������� �����", "�����������", "�������", "�����", "�������","�������","�������","�����������"};
    private int[] daysInt = {-1,0,1,2,3,4,5,6};
    public ActivityRoute() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listRoute = (ListView) findViewById(R.id.listViewRoute);
        sa = new SimpleAdapter(this, createRouteList(), android.R.layout.simple_expandable_list_item_2,
                new String[] {"name", "adress"},
                new int[] {android.R.id.text1, android.R.id.text2}
        );
        listRoute.setAdapter(sa);
        //listRoute.setItemsCanFocus(false);
        listRoute.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listRoute.setOnItemLongClickListener(new OnItemLongClickListener() {
                                                 public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                     onItemClick(parent, view, position, id);
                                                     return true;
                                                 }
                                             }
        );

        spinner = (Spinner) findViewById(R.id.spinnerDays);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("��� ������");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // ���������� ������� �������� ��������
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                fillWhereCondition(position);
                sa = new SimpleAdapter(getBaseContext(), createRouteList(), android.R.layout.simple_expandable_list_item_2,
                        new String[] {"name", "adress"},
                        new int[] {android.R.id.text1, android.R.id.text2}
                );
                listRoute.setAdapter(sa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Map<String, ? >> createRouteList()
    {
        List<Map<String, ? >> items = new ArrayList<Map<String, ? >>() ;
        outletsObjectList = new ArrayList<OutletObject>();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select outletId , outletName , VisitDay  , VisitDayId ,VisitOrder , CustomerId ,CustomerName, partnerId, partnerName, address from route" + routeWhere + " order by VisitDayId,outletName ", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++)
        {
            Map<String, Object> map= new HashMap<String, Object>();
            map.put("name", cursor.getString(cursor.getColumnIndex("outletName")));
            map.put("adress", cursor.getString(cursor.getColumnIndex("address")) + "  ����: "+cursor.getString(cursor.getColumnIndex("VisitDay")));
            items.add(map);
            OutletObject ob = new OutletObject();
            ob.customerId = UUID.fromString(cursor.getString(cursor.getColumnIndex("CustomerId")));
            ob.outletId = UUID.fromString(cursor.getString(cursor.getColumnIndex("outletId")));
            ob.partnerId = UUID.fromString(cursor.getString(cursor.getColumnIndex("partnerId")));
            ob.customerName = cursor.getString(cursor.getColumnIndex("CustomerName"));
            ob.outletName = cursor.getString(cursor.getColumnIndex("outletName"));
            ob.partnerName = cursor.getString(cursor.getColumnIndex("partnerName"));
            ob.outletAddress = cursor.getString(cursor.getColumnIndex("address"));
            outletsObjectList.add(ob);
            cursor.moveToNext();
        }
        return items;

    }


    private void onItemClick(AdapterView<?> parent, View arg1, int position, long id)
    {
        if (outletsObjectList != null)
        {
            appManager.getOurInstance().setActiveOutletObject(outletsObjectList.get(position));
          //  Toast.makeText(this, selectedOutlet.outletId.toString(), Toast.LENGTH_SHORT).show();
            showPopupMenu(arg1,outletsObjectList.get(position) );
        }
    }

    private void fillWhereCondition(int position)
    {
        if (daysInt[position] == -1) {
            routeWhere = "";
        }
        else {
            routeWhere = " where VisitDayId = "+ Integer.toString(daysInt[position]);
        }

    }

    private void showPopupMenu(View v, OutletObject outlet ) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_route, popupMenu.getMenu());
        popupMenu.getMenu().getItem(0).setTitle("������: "+ outlet.outletName);
        //popupMenu.inflate(R.menu.popupmenu_route);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.menuOrders:
                                Toast.makeText(getApplicationContext(),
                                        "�� ������� PopupMenu 1",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu2:
                                Toast.makeText(getApplicationContext(),
                                        "�� ������� PopupMenu 2",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu3:
                                Toast.makeText(getApplicationContext(),
                                        "�� ������� PopupMenu 3",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        // Force icons to show
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[] { boolean.class };
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {
            popupMenu.show();
            return;
        }

        popupMenu.show();
    }
}
