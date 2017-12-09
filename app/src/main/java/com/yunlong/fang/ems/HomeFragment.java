package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private String username;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");

        TextView textView =(TextView)view.findViewById(R.id.tv_home);
        textView.setText(username);

        return view;
    }
}
