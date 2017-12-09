package com.yunlong.fang.ems;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ems_student extends AppCompatActivity {
    private EditText zhanghao;
    private String sno,password;
    private EditText mima;
    private Button btn_login;
    String conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ems_student);
        zhanghao = (EditText)findViewById(R.id.username);
        mima = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sno = zhanghao.getText().toString();
                password = mima.getText().toString();
                MyTask myTask = new MyTask();
                myTask.execute(sno,password);
            }
        });
    }
    class MyTask extends AsyncTask<String,Integer,String> {
        String result = "";
        @Override
        protected String doInBackground(String... params) {
            String param = "username="+params[0]+"&password="+params[1];
            return this.sendPost("http://192.168.155.2:8080/ems_app/StudentLogin",param);
        }
        public String sendPost(String url,String params){

            try {
                URL realurl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection)realurl.openConnection();
                conn.setConnectTimeout(6000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                out.write(params.getBytes());
                out.flush();
                out.close();
                Log.e("conn","conn.getResponseCode()");
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null){
                    result += line;
                    if (line.contains("登录成功")){
                        String classid = result.substring(4);
                        result = "登陆成功";
                        Intent intent = new Intent(ems_student.this,HomePage.class);
                        Log.i("result",result);
                        intent.putExtra("username",sno);
                        intent.putExtra("password",password);
                        intent.putExtra("classid",classid);
                        startActivity(intent);
                    }
               }
            } catch (Exception e) {
                Log.e("fail",e.toString());
            }
            return result.trim();
        }
        @Override
        protected void onPostExecute(String s) {
                Toast.makeText(ems_student.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
