package com.example.day09hunaxin;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_pwd;
    private Button bu_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bu_regist = (Button) findViewById(R.id.bu_regist);

        bu_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_regist:
                regist();
                break;
        }
    }

    private void regist() {
        String name = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(name,pwd);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("TAG", "run: hah ");
                                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                RegistActivity.this.finish();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }.start();
        } else {
            Toast.makeText(this, "账号密码不为空", Toast.LENGTH_SHORT).show();
        }

    }
}
