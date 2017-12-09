package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.CourseInfo;
import utils.GetCourseInfo;

public class CourseList extends AppCompatActivity {
    private String classid;
    private List<CourseInfo> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Intent intent = getIntent();
        classid = intent.getStringExtra("classid");
        ListView listView = (ListView)findViewById(R.id.couserList);
        String urlpath = "http://192.168.65.2:8080/ems_app/CourseInfo?classid="+classid;
        try {
            info = GetCourseInfo.getCourseInfo(urlpath);
            List<HashMap<String, Object>> list = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("title",getResources().getString(R.string.coursename));
            map.put("info",getResources().getString(R.string.tname));
            list.add(map);
            for (int i = 0; i < info.size(); i++) {
                map = new HashMap<>();
                map.put("title", info.get(i).getCour_name());
                map.put("info", info.get(i).getTname());
                Log.i("aa",list.get(i).get("title").toString());
                list.add(map);
            }

            SimpleAdapter adapter = new SimpleAdapter(CourseList.this,list,R.layout.student_item,
                    new String[]{"title","info"},new int[]{R.id.title,R.id.info});
            listView.setAdapter(adapter);
        } catch (Exception e) {

            Log.i("失败", e.toString());
        }
    }
}
