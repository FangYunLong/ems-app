package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FunctionFragment extends Fragment {
    private Button loginInfo;
    private boolean flag = false;
    private String username,password,classid;
    private Button score,course,updatePassword;
    private View student_view;
    public FunctionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        student_view =inflater.inflate(R.layout.fragment_function_stu,container,false);
        Intent intent = getActivity().getIntent();
        classid = intent.getStringExtra("classid");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");



        if (true) return student_view;
        else return inflater.inflate(R.layout.fragment_function, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (true){

            score = (Button)getActivity().findViewById(R.id.score);

            score.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("username",username);
                    intent.setClass(getActivity().getApplicationContext(),ScoreList.class);
                    startActivity(intent);
                }
            });

            updatePassword = (Button)getActivity().findViewById(R.id.password);

            updatePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    intent.setClass(getActivity().getApplicationContext(),UpdatePassword.class);
                    startActivity(intent);
                }
            });

            course = (Button)getActivity().findViewById(R.id.course);

            course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("classid",classid);
                    intent.setClass(getActivity().getApplicationContext(),CourseList.class);
                    startActivity(intent);
                }
            });

        }
        else {

        }
    }
}
