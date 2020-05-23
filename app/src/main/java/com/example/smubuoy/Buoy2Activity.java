package com.example.smubuoy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Buoy2Activity extends AppCompatActivity implements View.OnClickListener{
    private TextView b2Wind,b2Temp,b2Dip,b2Power,b2Time;
    private ImageView ivIcon;
    private List<WeatherInfo> infoList;//水文信息数据集合
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buoy2);
        infoList=JsonParse2.getInstance().getInfosFromJson(Buoy2Activity.this);
        initView();
        getTimeData("00:00");//第一次进入应用时，显示00:00时刻水文信息
        //下面if条件判断用于标题栏返回键操作
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //自定义Toast显示位置,一定要在类的首部定义Toast变量。
        toast = Toast.makeText(getApplicationContext(), "点击并左右滑动底端时刻表，即可显示对应时刻水文信息！",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 15, 500);
        toast.show();
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
        ivIcon=(ImageView) findViewById(R.id.iv_icon);
        b2Wind=(TextView) findViewById(R.id.b2_wind);
        b2Temp=(TextView) findViewById(R.id.b2_temp);
        b2Dip=(TextView) findViewById(R.id.b2_dip);
        b2Power=(TextView) findViewById(R.id.b2_power);
        b2Time=(TextView)  findViewById(R.id.b2_time);
        findViewById(R.id.btn_00).setOnClickListener(this);
        findViewById(R.id.btn_01).setOnClickListener(this);
        findViewById(R.id.btn_02).setOnClickListener(this);
        findViewById(R.id.btn_03).setOnClickListener(this);
        findViewById(R.id.btn_04).setOnClickListener(this);
        findViewById(R.id.btn_05).setOnClickListener(this);
        findViewById(R.id.btn_06).setOnClickListener(this);
        findViewById(R.id.btn_07).setOnClickListener(this);
        findViewById(R.id.btn_08).setOnClickListener(this);
        findViewById(R.id.btn_09).setOnClickListener(this);
        findViewById(R.id.btn_10).setOnClickListener(this);
        findViewById(R.id.btn_11).setOnClickListener(this);
        findViewById(R.id.btn_12).setOnClickListener(this);
        findViewById(R.id.btn_13).setOnClickListener(this);
        findViewById(R.id.btn_14).setOnClickListener(this);
        findViewById(R.id.btn_15).setOnClickListener(this);
        findViewById(R.id.btn_16).setOnClickListener(this);
        findViewById(R.id.btn_17).setOnClickListener(this);
        findViewById(R.id.btn_18).setOnClickListener(this);
        findViewById(R.id.btn_19).setOnClickListener(this);
        findViewById(R.id.btn_20).setOnClickListener(this);
        findViewById(R.id.btn_21).setOnClickListener(this);
        findViewById(R.id.btn_22).setOnClickListener(this);
        findViewById(R.id.btn_23).setOnClickListener(this);
    }
    /**
     * 设置界面数据
     */
    private void  setData(WeatherInfo info){
        if(info==null) return;
        b2Wind.setText("风速||向: "+info.getWind());
        b2Temp.setText("水温: "+info.getTemp());
        b2Dip.setText("倾角："+info.getDip());
        b2Power.setText("电量:"+info.getPower());
       b2Time.setText(info.getTime());//此处time来源于b2time
        if(("00:00").equals(info.getTime())||("01:00").equals(info.getTime())||("02:00").equals(info.getTime())||("03:00").equals(info.getTime())
                ||("04:00").equals(info.getTime())||("20:00").equals(info.getTime())|| ("21:00").equals(info.getTime())||("22:00").equals(info.getTime())
                ||("23:00").equals(info.getTime())){
            ivIcon.setImageResource(R.drawable.moon);
        }else if(("05:00").equals(info.getTime())||("06:00").equals(info.getTime())||("07:00").equals(info.getTime())||("19:00").equals(info.getTime())
                ||("18:00").equals(info.getTime())){
            ivIcon.setImageResource(R.drawable.cloud_sun);
        }else if(("08:00").equals(info.getTime())||("09:00").equals(info.getTime())||("10:00").equals(info.getTime())||("11:00").equals(info.getTime())
                ||("12:00").equals(info.getTime())||("13:00").equals(info.getTime())||("14:00").equals(info.getTime())||("15:00").equals(info.getTime())
                ||("16:00").equals(info.getTime())||("17:00").equals(info.getTime())){
            ivIcon.setImageResource(R.drawable.sun);
        }
    }
    /**
     * 根据浮标号获取对应的水文信息
     */
    private void getTimeData(String time){
        for(WeatherInfo info:infoList){
            if(info.getTime().equals(time)){
                setData(info);
            }
        }
    }
    @Override
    public void onClick(View v){//按钮的点击事件
        switch (v.getId()){
            case R.id.btn_00://00:00时刻按钮的点击事件
                getTimeData("00:00");
                break;
            case R.id.btn_01://01:00时刻按钮的点击事件
                getTimeData("01:00");
                break;
            case R.id.btn_02://02:00时刻按钮的点击事件
                getTimeData("02:00");
                break;
            case R.id.btn_03://03:00时刻按钮的点击事件
                getTimeData("03:00");
                break;
            case R.id.btn_04://04:00时刻按钮的点击事件
                getTimeData("04:00");
                break;
            case R.id.btn_05://05:00时刻按钮的点击事件
                getTimeData("05:00");
                break;
            case R.id.btn_06://06:00时刻按钮的点击事件
                getTimeData("06:00");
                break;
            case R.id.btn_07://07:00时刻按钮的点击事件
                getTimeData("07:00");
                break;
            case R.id.btn_08://08:00时刻按钮的点击事件
                getTimeData("08:00");
                break;
            case R.id.btn_09://09:00时刻按钮的点击事件
                getTimeData("09:00");
                break;
            case R.id.btn_10://10:00时刻按钮的点击事件
                getTimeData("10:00");
                break;
            case R.id.btn_11://11:00时刻按钮的点击事件
                getTimeData("11:00");
                break;
            case R.id.btn_12://12:00时刻按钮的点击事件
                getTimeData("12:00");
                break;
            case R.id.btn_13://13:00时刻按钮的点击事件
                getTimeData("13:00");
                break;
            case R.id.btn_14://14:00时刻按钮的点击事件
                getTimeData("14:00");
                break;
            case R.id.btn_15://15:00时刻按钮的点击事件
                getTimeData("15:00");
                break;
            case R.id.btn_16://16:00时刻按钮的点击事件
                getTimeData("16:00");
                break;
            case R.id.btn_17://17:00时刻按钮的点击事件
                getTimeData("17:00");
                break;
            case R.id.btn_18://18:00时刻按钮的点击事件
                getTimeData("18:00");
                break;
            case R.id.btn_19://19:00时刻按钮的点击事件
                getTimeData("19:00");
                break;
            case R.id.btn_20://20:00时刻按钮的点击事件
                getTimeData("20:00");
                break;
            case R.id.btn_21://21:00时刻按钮的点击事件
                getTimeData("22:00");
                break;
            case R.id.btn_22://22:00时刻按钮的点击事件
                getTimeData("22:00");
                break;
            case R.id.btn_23://23:00时刻按钮的点击事件
                getTimeData("23:00");
                break;

        }
    }
}
