package com.example.smubuoy;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeviceActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText ownerEdit;
    private EditText basicEdit;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Toast.makeText(this, "设备信息填写完整后，请点击下方蓝色按钮进行保存！", Toast.LENGTH_SHORT).show();
        //下面是设备管理信息保存操作*******************************************
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        ownerEdit=(EditText)  findViewById(R.id.linear1_edit1);
        basicEdit=(EditText) findViewById(R.id.linear3_edit1);
        save=(Button) findViewById(R.id.save_text);
        String owner=pref.getString("owner","");
        String basic=pref.getString("basic","");
        ownerEdit.setText(owner);//记住设备所属方关键语句
        basicEdit.setText(basic);//记住设备基本信息关键语句

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner=ownerEdit.getText().toString();
                String basic=basicEdit.getText().toString();
                if(owner.length()>0&&basic.length()>0){
                    editor=pref.edit();
                    editor.putString("owner",owner);
                    editor.putString("basic",basic);
                    editor.apply();
                    Toast.makeText(DeviceActivity.this,"信息保存成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DeviceActivity.this,"信息内容不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //隐藏掉默认标题栏**************************************************
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

    }
    //对应默认标题栏操作返回键***********
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish(); // 给标题栏添加back button
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
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
