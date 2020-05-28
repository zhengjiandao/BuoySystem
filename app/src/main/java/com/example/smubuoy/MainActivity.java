package com.example.smubuoy;

//原有item导入包************************
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends BaseActivity {
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Query=(Button)  findViewById(R.id.btn_query);
        Button Gps=(Button) findViewById(R.id.btn_gps);
//        TextView view=(TextView) findViewById(R.id.view);
//        view.setBackgroundResource(R.drawable.text_view_border);//给TextView设置透明背景、圆角边框
        //Query.setBackgroundColor(Color.parseColor("#B0C4DE"));//自定代码设置按钮背景色
        //Query.setBackgroundResource(R.drawable.text_view_border);//边框圆角化
        Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BuoyActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //Gps.setBackgroundResource(R.drawable.text_view_border);//边框圆角化
        Gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BaiduMapActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //自定义Toast显示位置，一定要在类的首部定义Toast变量。
        toast = Toast.makeText(getApplicationContext(), "点击底部按钮，即可\n查看相关信息！",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 6, 500);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.map_item:
                Intent intent1=new Intent(this,DeviceActivity.class);
                startActivity(intent1);
                //Toast.makeText(this, "设备信息填写完整后，请点击下方蓝色按钮进行保存！", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.remove_item:
                //Toast.makeText(this, "您即将退出浮标系统账号", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent2);
             break;
            default:
        }
        return true;
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


