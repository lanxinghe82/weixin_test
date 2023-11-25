package com.example.weixin_two;

import android.app.Fragment;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class Fragment_Tx extends Fragment {
    private ListView lv;
    private ArrayList datas;
    public static List<String> txName = new ArrayList<String>();
    public static List<Integer>  imgId = new ArrayList<Integer>();
    public  static int[ ] iconArray = {
            R.drawable.jionfriend,
            R.drawable.qunliao,
            R.drawable.biaoqian,
            R.drawable.gongzhonghao,
            R.drawable.txfi,
            R.drawable.txs,
            R.drawable.txse,
            R.drawable.touxiang,
            R.drawable.touxiang,
            R.drawable.tx,
            R.drawable.txt,
            R.drawable.txw,
            R.drawable.txf,
            R.drawable.txfi,
            R.drawable.txs,
            R.drawable.txse,
            R.drawable.touxiang,
            R.drawable.touxiang,
            R.drawable.txs,
            R.drawable.txse,
            R.drawable.touxiang,
            R.drawable.txw,
            R.drawable.txf,
            R.drawable.txfi,
            R.drawable.txs,
            R.drawable.txse,
            R.drawable.touxiang};
    public static String [] starArray =  {
            "新的朋友","群聊","标签","公众号","人生","得意","计算机","懒货","没人",
            "鬼","君子","小人","非人","好人","坏人","85","17","18","公众号","人生","得意","计算机",
            "君子","小人","非人","好人","坏人"
    };
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dl2,null);
        lv = view.findViewById(R.id.lv);
        lv.setAdapter(new MyAdpter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = starArray[i];
                int img = iconArray[i];
                Intent intent_1 = new Intent();
                intent_1.putExtra("name",name);
                intent_1.putExtra("img",img);
                intent_1.setClass(getContext(), liaotian.class);
                startActivity(intent_1);
            }
        });
        return view;
    }

    private class MyAdpter extends BaseAdapter {

        @Override
        public int getCount() {
            return imgId.size();
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
            if ((i+1)%5==0) {
                v = View.inflate(getContext(), R.layout.dl4, null);
            } else {
                v = View.inflate(getContext(), R.layout.dl3, null);
                ImageView imageView = v.findViewById(R.id.imageview);
                TextView textView = v.findViewById(R.id.textview);
                imageView.setImageResource(imgId.get(i));
                textView.setText(txName.get(i));
            }
            return v;
        }
    }
}
