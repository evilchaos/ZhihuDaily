package com.example.liujiachao.zhihudaily.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liujiachao.zhihudaily.R;

/**
 * Created by evilchaos on 16/9/4.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView sinaLogin;
    private TextView tencentLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        sinaLogin = (TextView)findViewById(R.id.sina_login);
        sinaLogin.setOnClickListener(this);
        tencentLogin = (TextView)findViewById(R.id.tencent_login);
        tencentLogin.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));
        toolbar.setTitle("登录");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sina_login:
                Toast.makeText(this, "暂无新浪微博登录功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tencent_login:
                Toast.makeText(this,"暂无腾迅微博登录功能",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
