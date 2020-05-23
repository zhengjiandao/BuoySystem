package com.example.smubuoy;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParse2 {
    private static JsonParse2 instance;

    private JsonParse2() {

    }

    public static JsonParse2 getInstance() {
        if (instance == null) {
            instance = new JsonParse2();
        }
        return instance;
    }

    /**
     * 将获取的数据流转化为JSON数据
     */
    private String read(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();    //实例化一个StringBuilder对象
            //用InputStreamReader把in这个字节流转换成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            //判断从reader中读取的行内容是否为空
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (in != null) in.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //解析json文件返回水文信息的集合
    public List<WeatherInfo> getInfosFromJson(Context context) {
        List<WeatherInfo> weatherInfos = new ArrayList<>();
        InputStream is = null;
        try {
            //从项目中的assets文件中获取json文件
            is = context.getResources().getAssets().open("buoy2.json");
            String json = read(is);       //获取json数据
            Gson gson = new Gson();       //创建Gson对象
            //创建一个TypeToken的匿名子类对象，并调用该对象的getType()方法
            Type listType = new TypeToken<List<WeatherInfo>>() {
            }.getType();
            //把获取到的信息集合存到infoList中
            List<WeatherInfo> infoList = gson.fromJson(json, listType);
            return infoList;
        } catch (Exception e) {
        }
        return weatherInfos;
    }
}
