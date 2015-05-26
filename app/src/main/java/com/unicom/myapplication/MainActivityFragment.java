package com.unicom.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View parentView ;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main, container, false);

        return parentView;
    }
}
