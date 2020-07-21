package com.example.day09hunaxin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.day09hunaxin.utils.SpUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_pwd;
    private Button bu_login;
    private Button bu_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bu_login = (Button) findViewById(R.id.bu_login);
        bu_regist = (Button) findViewById(R.id.bu_regist);

        bu_login.setOnClickListener(this);
        bu_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_login:
                login();
                break;
            case R.id.bu_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
        }
    }

    private void login() {
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            EMClient.getInstance().login(name, pwd, new EMCallBack() {
                @Override
                public void onSuccess() {//成功
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    //Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    SpUtils.setParam(LoginActivity.this, "name", name);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onError(int i, String s) {//失败
                    new Thread() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }.start();
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });

        }
    }


}
