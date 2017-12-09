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

public class UpdatePassword extends AppCompatActivity {
    private String uname,pwd,p1,p2;
    private EditText password1,password2;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        Intent intent = getIntent();
        uname = intent.getStringExtra("username");
        pwd = intent.getStringExtra("password");
        password1 = (EditText)findViewById(R.id.password1);
        password2 = (EditText)findViewById(R.id.password2);
        update = (Button)findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1 = password1.getText().toString();
                p2 = password2.getText().toString();
                UpdatePassword.MyTask myTask = new UpdatePassword.MyTask();
                if (p1.equals(p2)) {
                    myTask.execute(uname, p1);
                }
            }
        });


    }

    class MyTask extends AsyncTask<String,Integer,String> {
        String result = "";
        @Override
        protected String doInBackground(String... params) {
            String param = "username="+params[0]+"&password="+params[1];
            return this.sendPost("http://192.168.155.2:8080/ems_app/UpdatePassword",param);
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
                    if (line.contains("修改成功")){
                        Log.i("result",result);
                    }
                }
            } catch (Exception e) {
                Log.e("fail",e.toString());
            }
            return result.trim();
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(UpdatePassword.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
