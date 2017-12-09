package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.ScoreInfo;
import utils.GetScoreInfo;

public class ScoreList extends AppCompatActivity {
    private String username;
    private List<ScoreInfo> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
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
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        ListView listView = (ListView)findViewById(R.id.listView);
        String urlpath = "http://192.168.65.2:8080/ems_app/ScoreInfo?username="+username;
        try {
            info = GetScoreInfo.getScoreInfo(urlpath);

            List<HashMap<String, Object>> list = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("title",getResources().getString(R.string.coursename));
            map.put("info",getResources().getString(R.string.score));
            list.add(map);
                for (int i = 0; i < info.size(); i++) {
                    map = new HashMap<>();
                    map.put("title", info.get(i).getCour_name());
                    map.put("info", info.get(i).getScore());
                    Log.i("aa",list.get(i).get("title").toString());
                    list.add(map);
                }

            SimpleAdapter adapter = new SimpleAdapter(ScoreList.this,list,R.layout.student_item,
                    new String[]{"title","info"},new int[]{R.id.title,R.id.info});
            listView.setAdapter(adapter);
        } catch (Exception e) {

            Log.i("失败", e.toString());
        }
    }
}
