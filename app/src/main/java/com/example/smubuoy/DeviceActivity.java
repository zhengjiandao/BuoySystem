package com.example.smubuoy;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        //下面if条件判断用于标题栏返回键操作***********************************
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //下面是设备管理信息保存操作*******************************************
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        ownerEdit=(EditText)  findViewById(R.id.linear1_edit1);
        basicEdit=(EditText) findViewById(R.id.linear3_edit1);
        save=(Button) findViewById(R.id.save_text);
        //设置按钮背景色
        save.setBackgroundColor(Color.parseColor("#4169E1"));
        String owner=pref.getString("owner","");
        String basic=pref.getString("basic","");
        ownerEdit.setText(owner);//记住设备所属方关键语句
        basicEdit.setText(basic);//记住设备基本信息关键语句

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner=ownerEdit.getText().toString();
                String basic=basicEdit.getText().toString();
                editor=pref.edit();
                editor.putString("owner",owner);
                editor.putString("basic",basic);
                editor.apply();
                Toast.makeText(DeviceActivity.this,"信息保存成功！",Toast.LENGTH_SHORT).show();
            }
        });
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
}
