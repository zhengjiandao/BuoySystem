package com.example.smubuoy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private Button logoff;

    private CheckBox rememberPass;

    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit=(EditText)  findViewById(R.id.account);
        passwordEdit=(EditText) findViewById(R.id.password);
        rememberPass=(CheckBox) findViewById(R.id.remember_pass);
        login=(Button) findViewById(R.id.login);
        logoff=(Button) findViewById(R.id.logoff);
        //设置按钮背景色
        login.setBackgroundColor(Color.parseColor("#3CB371"));
        boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember) {
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);//记住账户关键语句
            passwordEdit.setText(password);//记住密码关键语句
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if (account.equals("admin")&&password.equals("123456")){
                    editor=pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this,"账户名或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    //退出按钮窃听器
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count==1){
                    Toast.makeText(LoginActivity.this,"再点击一次，您将退出海大智能浮标系统！",Toast.LENGTH_SHORT).show();
                } else if(count==2){
                    ActivityCollector.finishAll();
                }
            }
        });
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
