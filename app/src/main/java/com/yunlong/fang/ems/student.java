package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import domain.StudentInfo;
import utils.GetStudentInfo;

public class student extends AppCompatActivity {
    private TextView t1,t2;
    private StudentInfo studentinfo;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
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
        String urlpath = "http://192.168.65.2:8080/ems_app/StudentInfo?username="+username;

        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.birthday);

        try {
            studentinfo = GetStudentInfo.getStudentInfo(urlpath);

            t1.setText(studentinfo.getStudent_name());
            t2.setText(studentinfo.getBirthday());
        } catch (Exception e) {

            Log.i("失败", e.toString());
        }
    }
}
