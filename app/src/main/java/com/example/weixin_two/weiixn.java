package com.example.weixin_two;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class weiixn extends AppCompatActivity {

   // Button button_1 ;
    ListView lv_2;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixinview);
        //button_1 = findViewById(R.id.tongxunlu);
        lv_2 = findViewById(R.id.lv_2);
        lv_2.setAdapter(new MyAdpter());
        lv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_10 = new Intent();
                intent_10.putExtra("i",i);
                intent_10.setClass(weiixn.this, liaotian.class);
                startActivity(intent_10);
            }
        } );
       // button_1.setOnClickListener(new MyClickTX());
    }

//    private class MyClickTX implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            Intent intent_1 = new Intent();
//            intent_1.setClass(weiixn.this, tongxun.class);
//            startActivity(intent_1);
//        }
//    }

    private class MyAdpter extends BaseAdapter {

            @Override
            public int getCount() {
                return liaotian.quanBuimg.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View v;
                if (view == null) {
                    v = View.inflate(getApplicationContext(), R.layout.weixintiaomu, null);
                } else {
                    v = view;
                }
                ImageView imageView = v.findViewById(R.id.iv_2);
                TextView textView = v.findViewById(R.id.tv_4);
                imageView.setImageResource(liaotian.quanBuimg.get(i));
                textView.setText(liaotian.quanButext.get(i));
                return v;
            }
    }
}
