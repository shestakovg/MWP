package com.unicom.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.unicom.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Entitys.OutletObject;

public class ActivityRoute extends ActionBarActivity {

    private ListView listRoute;
    private List<OutletObject> outletsObjectList;
    private String[] outletsId;
    private OutletObject selectedOutlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listRoute = (ListView) findViewById(R.id.listViewRoute);
        SimpleAdapter sa = new SimpleAdapter(this, createRouteList(), android.R.layout.simple_expandable_list_item_2,
                new String[] {"name", "adress"},
                new int[] {android.R.id.text1, android.R.id.text2}
                );
        listRoute.setAdapter(sa);
        listRoute.setItemsCanFocus(false);
        listRoute.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listRoute.setOnItemLongClickListener(new OnItemLongClickListener() {
                                                 public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                     onItemClick(parent, view, position,id);
                                                     return true;
                                                 }
                                             }
        );
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
        for (int i = 0; i < 100; i++)
        {
            Map<String, Object> map= new HashMap<String, Object>();
            map.put("name", "Торговая точка " + Integer.toString(i));
            map.put("adress", "Адрес " + Integer.toString(i));
            items.add(map);
            OutletObject ob = new OutletObject();
            ob.customerId = UUID.randomUUID();
            ob.outletId = UUID.randomUUID();
            ob.customerName = "Customer "+Integer.toString(i);
            ob.outletName = "Торговая точка " + Integer.toString(i);
            outletsObjectList.add(ob);
        }
        return items;

    }


    private void onItemClick(AdapterView<?> parent, View arg1, int position, long id)
    {
        if (outletsObjectList != null)
        {
            selectedOutlet = outletsObjectList.get(position);
            Toast.makeText(this, selectedOutlet.outletName, Toast.LENGTH_SHORT).show();
        }
    }
}
