package com.example.smubuoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//天气信息导入包**************************
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

//public class BuoyActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buoy);
//    }
//}
public class BuoyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvColumn,tvWeather,tvTemp,tvWind,tvPm;
    private ImageView ivIcon;
    private List<WeatherInfo> infoList;//天气预报数据集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buoy);
        infoList=JsonParse.getInstance().getInfosFromJson(BuoyActivity.this);
        initView();
        getColumnData("风速||向");//第一次进入应用时，显示风速信息
        //下面if条件判断用于标题栏返回键操作
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // 给标题栏添加back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(){
        tvColumn=(TextView) findViewById(R.id.tv_column);
        tvWeather=(TextView) findViewById(R.id.tv_weather);
        tvTemp=(TextView) findViewById(R.id.tv_temp);
        tvWind=(TextView) findViewById(R.id.tv_wind);
        tvPm=(TextView) findViewById(R.id.tv_pm);
        ivIcon=(ImageView) findViewById(R.id.iv_icon);
        findViewById(R.id.btn_sw).setOnClickListener(this);
        findViewById(R.id.btn_fsx).setOnClickListener(this);
        findViewById(R.id.btn_qj).setOnClickListener(this);
        findViewById(R.id.btn_dl).setOnClickListener(this);
    }
    /**
     * 设置界面数据
     */
    private void  setData(WeatherInfo info){
        if(info==null) return;
        tvColumn.setText(info.getColumn());
        tvWeather.setText(info.getWeather());
        tvTemp.setText(info.getTemp());
        tvWind.setText("风力："+info.getWind());
        tvPm.setText("PM:"+info.getPm());
        if(("晴转多云").equals(info.getWeather())){
            ivIcon.setImageResource(R.drawable.cloud_sun);
        }else if(("多云").equals(info.getWeather())){
            ivIcon.setImageResource(R.drawable.clouds);
        }else if(("晴").equals(info.getWeather())){
            ivIcon.setImageResource(R.drawable.sun);
        }
    }
    /**
     * 根据信息栏获取对应的天气信息
     */
    private void getColumnData(String column){
        for(WeatherInfo info:infoList){
            if(info.getColumn().equals(column)){
                setData(info);
            }
        }
    }
    @Override
    public void onClick(View v){//按钮的点击事件
        switch (v.getId()){
            case R.id.btn_sw://水温按钮的点击事件
                getColumnData("水温");
                break;
            case R.id.btn_fsx://风速/向按钮的点击事件
                getColumnData("风速||向");
                break;
            case R.id.btn_qj://浮标倾角按钮的点击事件
                getColumnData("倾角");
                break;
            case R.id.btn_dl://浮标电量按钮的点击事件
                getColumnData("电量");
                break;
        }
    }
}

