package com.example.weixin_two;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWriter {

    public static void jsonWrite (int[] iconArray, String [] starArray, Context context) {
        try {
            // 创建一个JSON对象
            JSONObject jsonObject = new JSONObject();
            for (int i = 0 ;  i < iconArray.length ; i++) {
                // 添加键值对
                jsonObject.put("txName" + i + "", starArray[i]);
                jsonObject.put("imgId" + i + "", iconArray[i]);
            }
            // 将JSON对象写入文件
            String path = context.getFilesDir().getPath();
            File file = new File(path,"data.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(jsonObject.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jsonRead (String url_json) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 读取JSON文件内容
                    URL url = new URL(url_json);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String content = StringTools.readStream(inputStream);
                        // 将内容转换为JSON对象
                        JSONObject jsonObject = new JSONObject(content);
                        // 获取JSON对象中的键值对
                        for (int i = 0; i <Fragment_Tx.iconArray.length;i++) {
                            String name = jsonObject.getString("txName" + i + "");
                            int img = jsonObject.getInt("imgId" + i + "");
                            Fragment_Tx.txName.add(name);
                            Fragment_Tx.imgId.add(img);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }){}.start();
    }
}
