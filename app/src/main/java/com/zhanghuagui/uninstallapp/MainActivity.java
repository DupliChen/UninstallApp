package com.zhanghuagui.uninstallapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.iv_operate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Item> items = Helper.loadClearItems();
                if (items.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.uninstall_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Item item : items) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_DELETE);//设置action为卸载已安装的包
                    intent.setData(Uri.parse("package:" + item.getPackageName()));
                    startActivity(intent);
                }
            }
        });
    }
}
