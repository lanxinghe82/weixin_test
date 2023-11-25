package com.example.weixin_two;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;

public class all extends AppCompatActivity {

    Button button_weixin;
    Button button_tongxun;
    Button button_faxian;
    Button button_me;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_fragment);
        button_weixin = findViewById(R.id.weixin);
        button_tongxun = findViewById(R.id.tongxunlu);
        button_faxian = findViewById(R.id.faxian);
        button_me = findViewById(R.id.wo);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.ll_all,new Fragment_weixin());
        beginTransaction.commit();
        button_tongxun.setOnClickListener(new MyClickTX());
        button_weixin.setOnClickListener(new MyClickWX());
        button_faxian.setOnClickListener(new MyClickFX());
        button_me.setOnClickListener(new MyClickME());
    }

    private class MyClickTX implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(R.id.ll_all,new Fragment_Tx());
            beginTransaction.commit();
        }
    }

    private class MyClickWX implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(R.id.ll_all,new Fragment_weixin());
            beginTransaction.commit();
        }
    }

    private class MyClickFX implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(R.id.ll_all,new Fragment_FX());
            beginTransaction.commit();
        }
    }

    private class MyClickME implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(R.id.ll_all,new Fragment_me());
            beginTransaction.commit();
        }
    }
}
