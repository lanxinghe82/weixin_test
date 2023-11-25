package com.example.weixin_two;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Fragment_weixin extends Fragment {
    ListView lv_2;
    MyOpenHelper myOpenHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weixinview,null);
        lv_2 = view.findViewById(R.id.lv_2);
        lv_2.setAdapter(new MyAdpter());
        lv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_10 = new Intent();
                intent_10.putExtra("i",i+1);
                intent_10.setClass(getContext(), liaotian.class);
                startActivity(intent_10);
            }
        } );
        return view;
    }

    private class MyAdpter extends BaseAdapter {

        @Override
        public int getCount() {
            myOpenHelper = new MyOpenHelper(getContext());
            SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
            String sql = "select count(*) from chat";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            cursor.moveToFirst();
            long count = cursor.getLong(0);
            cursor.close();
            return (int) count;
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
                v = View.inflate(getContext(), R.layout.weixintiaomu, null);
            } else {
                v = view;
            }
            ImageView imageView = v.findViewById(R.id.iv_2);
            TextView textView = v.findViewById(R.id.tv_4);
            myOpenHelper = new MyOpenHelper(getContext());
            SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.query("chat",null,"_id=?",new String[]{String.valueOf(i+1)},null,null,null);
            cursor.moveToFirst();
            imageView.setImageResource(cursor.getInt(2));
            textView.setText(cursor.getString(3));
            return v;
        }
    }
}
