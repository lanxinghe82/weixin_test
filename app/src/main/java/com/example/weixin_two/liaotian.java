package com.example.weixin_two;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class liaotian extends AppCompatActivity {

    public static List<String> quanBuname = new ArrayList<String>();
    public static List<Integer>  quanBuimg = new ArrayList<Integer>();
    public static List<String>  quanButext = new ArrayList<String>();


    TextView haoyouname ;
    ImageView haoyouimg;
    TextView liaotianNR;
    EditText laiotian;
    Button button_1;
    String name;
    int img;
    int i;
    String text_11;
    MyOpenHelper myOpenHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixinliaotian);
        //myOpenHelper = new MyOpenHelper(getApplicationContext());
        haoyouname = findViewById(R.id.tv_2);
        haoyouimg = findViewById(R.id.iv);
        liaotianNR = findViewById(R.id.tv_3);
        laiotian = findViewById(R.id.et);
        button_1 = findViewById(R.id.button_1);
        Intent intent_1 = getIntent();
        //输入完内容跳转:
        i = intent_1.getIntExtra("i",-1);
        if (i == -1) {
            //从通讯录界面跳转过来的显示
            name = intent_1.getStringExtra("name");
            img = intent_1.getIntExtra("img",0);
            haoyouname.setText(name);
            if (img != 0) {
                haoyouimg.setImageResource(img);
            }
        } else {
            myOpenHelper = new MyOpenHelper(getApplicationContext());
            SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.query("chat",null,"_id=?",new String[]{String.valueOf(i)},null,null,null);
            cursor.moveToFirst();
            name = cursor.getString(1);
            img = cursor.getInt(2);
            text_11 = cursor.getString(3);
            haoyouname.setText(name);
            liaotianNR.setText(text_11);
            if (img != 0) {
                haoyouimg.setImageResource(img);
            }
        }
        button_1.setOnClickListener(new MyClick_3());
    }
        private class MyClick_3 implements View.OnClickListener {

            @Override
            public void onClick(View view) {
                myOpenHelper = new MyOpenHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
                if ( i == -1 ) {
                    String text_1 = laiotian.getText().toString().trim();
                    if (text_1.equals("")) {
                        Toast.makeText(getApplicationContext(),"输入的内容不能为空",Toast.LENGTH_LONG).show();
                    } else {
//                        quanButext.add(text_1);
//                        quanBuname.add(name);
//                        quanBuimg.add(img);
                        ContentValues values = new ContentValues();
                        values.put("name",name);
                        values.put("img",img);
                        values.put("text",text_1);
                        long insert = sqLiteDatabase.insert("chat",null,values);
//                        Intent intent_2 = new Intent();
//                        intent_2.setClass(liaotian.this, all.class);
//                        startActivity(intent_2);
                        finish();
                    }
                } else {
                    String text_1 = laiotian.getText().toString().trim();
                    if (text_1.equals("")) {
                        Toast.makeText(getApplicationContext(),"输入的内容不能为空",Toast.LENGTH_LONG).show();
                    } else {
//                      quanButext.set(i,text_1) ;
                        ContentValues values = new ContentValues();
                        values.put("text",text_1);
                        int update = sqLiteDatabase.update("chat",values,"_id=?",new String[]{String.valueOf(i)});
//                        Intent intent_2 = new Intent();
//                        intent_2.setClass(liaotian.this, all.class);
//                        startActivity(intent_2);
                        finish();
                    }
                }
            }
        }
}
