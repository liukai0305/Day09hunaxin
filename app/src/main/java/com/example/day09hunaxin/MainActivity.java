package com.example.day09hunaxin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day09hunaxin.adapter.RcyAdapter;
import com.example.day09hunaxin.utils.SpUtils;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView rcy;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
        initView();
        initData();
    }

    private void initData() {
        String param = (String) SpUtils.getParam(MainActivity.this, "name", "为登入");
        title.setText("现在的人是" + param);

        rcy.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("fff");
        RcyAdapter rcyAdapter = new RcyAdapter(list, this);
        rcy.setAdapter(rcyAdapter);
        rcyAdapter.setOnItemClickLis(new RcyAdapter.OnItemClickLis() {
            @Override
            public void click(int position) {
                String s = list.get(position);

            }
        });
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        rcy = (RecyclerView) findViewById(R.id.rcy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "推出等人员");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                EMClient.getInstance().logout(true);
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return super.onContextItemSelected(item);
    }
}
