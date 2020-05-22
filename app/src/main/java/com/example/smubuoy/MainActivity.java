package com.example.smubuoy;

//原有item导入包************************
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buoy1=(Button)  findViewById(R.id.btn_buoy1);
        Button buoy2=(Button)  findViewById(R.id.btn_buoy2);
        TextView view=(TextView) findViewById(R.id.view);
        view.setBackgroundResource(R.drawable.text_view_border);//给TextView设置透明背景、圆角边框
        //buoy1.setBackgroundColor(Color.parseColor("#B0C4DE"));//自定代码设置按钮背景色
        buoy1.setBackgroundResource(R.drawable.text_view_border);
        buoy2.setBackgroundResource(R.drawable.text_view_border);
        buoy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BuoyActivity.class);
                startActivityForResult(intent,1);
            }
        });
        buoy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Buoy2Activity.class);
                startActivityForResult(intent,1);
            }
        });
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
                Toast.makeText(this, "您即将打开坐标地图", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(this,MapActivity.class);
                //intent1.setData(Uri.parse("https://map.baidu.com/@13571410,3595029,15z"));
                startActivity(intent1);
                break;
            case  R.id.remove_item:
                Toast.makeText(this, "您即将退出浮标系统账号", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent2);
             break;
            default:
        }
        return true;
    }
}


