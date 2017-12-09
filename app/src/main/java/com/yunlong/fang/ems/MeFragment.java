package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.StudentInfo;
import utils.GetStudentInfo;

public class MeFragment extends Fragment {
    private String username;
    private StudentInfo info;

    public MeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");
        ListView listView = (ListView)view.findViewById(R.id.student_lv);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        String urlpath = "http://192.168.155.2:8080/ems_app/StudentInfo?username="+username;
        try {
            info = GetStudentInfo.getStudentInfo(urlpath);

            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map = new HashMap<>();
            map.put("title",getResources().getString(R.string.id));
            map.put("info",username);
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.name));
            map.put("info",info.getStudent_name());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.sex));
            map.put("info",info.getSex());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.dept_name));
            map.put("info",info.getDept_name());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.spec_name));
            map.put("info",info.getSpec_name());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.class_name));
            map.put("info",info.getClass_name());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.grade));
            map.put("info",info.getGrade());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.nation));
            map.put("info",info.getNation());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.id_card));
            map.put("info",info.getId_card());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.birthday));
            map.put("info",info.getBirthday());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.admission_date));
            map.put("info",info.getAdmission_date());
            list.add(map);
            map = new HashMap<>();
            map.put("title",getResources().getString(R.string.study_length));
            map.put("info",info.getStudy_length());
            list.add(map);
            SimpleAdapter adapter = new SimpleAdapter(getActivity(),list,R.layout.student_item,
                    new String[]{"title","info"},new int[]{R.id.title,R.id.info});
            listView.setAdapter(adapter);
        } catch (Exception e) {

            Log.i("失败", e.toString());
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
