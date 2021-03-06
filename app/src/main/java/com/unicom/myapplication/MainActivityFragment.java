package com.unicom.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View parentView ;
    Button btnSetup;
    Button btnSyncServer;
    Button btnBeginWork;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main, container, false);
        btnSetup = (Button) parentView.findViewById(R.id.btnSetup);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSetupClick(v);
            }
        });
        btnBeginWork = (Button) parentView.findViewById(R.id.btnBeginWork);
        btnBeginWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBeginWork(v);
            }
        });
        btnSyncServer = (Button) parentView.findViewById(R.id.btnSyncServer);
        btnSyncServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSyncServer(v);
            }
        });
        return parentView;
    }

    private void btnSetupClick(View v)
    {
        Intent intent = new Intent(getActivity(), SetupActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("w", selectedValue);
        startActivity(intent);
    }

    private void btnBeginWork(View v)
    {
        Intent intent = new Intent(getActivity(), ActivityRoute.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("w", selectedValue);
        startActivity(intent);
    }

    private void btnSyncServer(View v)
    {
        Intent intent = new Intent(getActivity(), ActivitySync.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("w", selectedValue);
        startActivity(intent);
    }

}
