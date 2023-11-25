package com.example.weixin_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText phone;
    private EditText pwd;
    CheckBox saveInfo;
    CheckBox autoLogin;
    String result = "";
    String all = "";
    String url_json = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl);
        SharedPreferences sp = getSharedPreferences("info",0);
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        saveInfo = findViewById(R.id.cb_saveInfo);
        autoLogin = findViewById(R.id.cb_autoLogin);
        String name = sp.getString("name","");
        String password = sp.getString("password","");
        boolean cb_saveInfo = sp.getBoolean("saveInfo",false);
        boolean cb_autoLogin = sp.getBoolean("autoLogin",false);
        phone.setText(name);
        pwd.setText(password);
        saveInfo.setChecked(cb_saveInfo);
        if (cb_autoLogin) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, all.class);
            startActivity(intent);
        }
        Button button = findViewById(R.id.denglu);
        button.setOnClickListener(new MyClickListener());
    }

    private class MyClickListener implements View.OnClickListener {
        //当点击时发生
        @Override
        public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String account = phone.getText().toString().trim();
                    String password = pwd.getText().toString().trim();
                    String path = "http://192.168.2.105:8080/login_Android/Login?name=" + account + "&password=" + password + "";
                    try {
                         URL url = new URL(path);
                         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream inputStream = conn.getInputStream();
                            all = StringTools.readStream(inputStream);
                            System.out.println(all);
                            String[] sssssss=all.split("##");
                            System.out.println(sssssss[0]);
                            result = sssssss[0];
                            System.out.println(sssssss[1]);
                            url_json = sssssss[1];
                         }
                        if (result.equals("sucess")) {
                            JsonWriter.jsonRead(url_json);
                            if (saveInfo.isChecked()) {
                               SharedPreferences sp = getSharedPreferences("info", 0);
                                //把数据保存起来
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString("name", account);
                                edit.putString("password", password);
                                edit.putBoolean("saveInfo", true);
                                edit.commit();
                            } else {
                                SharedPreferences sp = getSharedPreferences("info", 0);
                                 //把数据保存起来
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString("name", "");
                                edit.putString("password", "");
                                edit.putBoolean("saveInfo", false);
                                edit.commit();
                            }
                            if (autoLogin.isChecked()) {
                                SharedPreferences sp = getSharedPreferences("info", 0);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putBoolean("autoLogin", true);
                                edit.commit();
                            } else {
                                SharedPreferences sp = getSharedPreferences("info", 0);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putBoolean("autoLogin", false);
                                edit.commit();
                            }
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, all.class);
                            startActivity(intent);
                        } else if (result.equals("faile")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "账号或密码错误！",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "网络异常！",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                 }
            }){}.start();
        }
    }
}