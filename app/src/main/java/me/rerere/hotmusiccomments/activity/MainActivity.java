package me.rerere.hotmusiccomments.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.rerere.hotmusiccomments.R;
import me.rerere.hotmusiccomments.comments.Comment;
import me.rerere.hotmusiccomments.utils.CommentAPI;

public class MainActivity extends AppCompatActivity {

    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(0xff0000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(swipeRefreshLayout); // 刷新列表: 耗时操作
            }
        });

        ListView listView = findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        this.refresh(swipeRefreshLayout); // 启动刷新
    }

    public void refresh(SwipeRefreshLayout swipeRefreshLayout){
            new Thread(() -> {
                ListView listView = findViewById(R.id.listView1);
                ArrayAdapter arrayAdapter = (ArrayAdapter) listView.getAdapter();
                data.clear();
                // 设置控件更新状态
                runOnUiThread(() -> {
                    swipeRefreshLayout.setRefreshing(true);
                    arrayAdapter.notifyDataSetChanged();
                });

                // 获取数据开始更新
                for(int i = 0;i < 20;i++){
                    data.add(CommentAPI.getHotComment(10,20).getContent());
                    runOnUiThread(arrayAdapter::notifyDataSetChanged);
                }

                // 更新完成
                runOnUiThread(() -> {
                    arrayAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                });
            }).start();
    }
}
