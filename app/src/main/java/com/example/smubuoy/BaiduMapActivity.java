package com.example.smubuoy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class BaiduMapActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //下面if条件判断用于默认标题栏返回键操作*****************************
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        //下面是对百度地图的调用操作***************************************
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);//一定要将这语句放到此处，否则无法调用百度地图
        mapView=(MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new  String[permissionList.size()]);
            ActivityCompat.requestPermissions(BaiduMapActivity.this,permissions,1);
        }else {
            requestLocation();
        }
        //隐藏掉默认标题栏**************************************************
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }
    //重写默认标题栏返回设置函数*********************************************
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.back:
//                this.finish(); // 给标题栏添加back button
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //百度地图调用相关函数重写与创建********************************************************************
    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            //LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            LatLng ll=new LatLng(30.877808,121.910039);
            MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(19f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        //显示指定坐标***************************
//        MyLocationData.Builder locationBuilder1=new MyLocationData.Builder();
//        locationBuilder1.latitude(30.878902);
//        locationBuilder1.longitude(121.91094);
//        MyLocationData locationData1=locationBuilder1.build();
//        baiduMap.setMyLocationData(locationData1);
//        MyLocationData.Builder locationBuilder2=new MyLocationData.Builder();
//        locationBuilder2.latitude(30.876857);
//        locationBuilder2.longitude(121.907939);
//        MyLocationData locationData2=locationBuilder2.build();
//        baiduMap.setMyLocationData(locationData2);
        //在指定坐标添加标记**********************
        //定义Maker坐标点
        LatLng point1 = new LatLng(30.878902, 121.91094);
        LatLng point2 = new LatLng(30.876857, 121.907939);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.map_marker);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option1= new MarkerOptions()
                .position(point1)
                .icon(bitmap);
        OverlayOptions option2= new MarkerOptions()
                .position(point2)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option1);
        baiduMap.addOverlay(option2);
        //在图标上标记文字备注********************
        //文字覆盖物位置坐标
        LatLng llText1 = new LatLng(30.878902, 121.91104);
        LatLng llText2 = new LatLng(30.876857, 121.908039);
        //构建TextOptions对象
        OverlayOptions mTextOptions1 = new TextOptions()
                .text("浮标1") //文字内容
                .bgColor(0x19e64dff) //背景色透明
                .fontSize(36) //字号
                .fontColor(0xFFFF00FF) //文字颜色
                .rotate(0) //旋转角度
                .position(llText1);
        OverlayOptions mTextOptions2 = new TextOptions().text("浮标2").bgColor(0x19e64dff)
                .fontSize(36).fontColor(0xFFFF00FF).rotate(0).position(llText2);
        //在地图上显示文字覆盖物
        Overlay mText1 = baiduMap.addOverlay(mTextOptions1);
        Overlay mText2 = baiduMap.addOverlay(mTextOptions2);
    }
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//强制采用GPS定位设置
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result:grantResults){
                        if (result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public  void onReceiveLocation(BDLocation location){
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
        }
    }

    //触发手机返回键两次退出整个App系统****************************************************************
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onAppExit();
            return true;
        }
        return false;
    }

    private long firstClick;
    public void onAppExit() {
        if (System.currentTimeMillis() - this.firstClick > 2000L) {
            this.firstClick = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出海大智能浮标系统", Toast.LENGTH_LONG).show();
            return;
        }
        finish();
    }

}
