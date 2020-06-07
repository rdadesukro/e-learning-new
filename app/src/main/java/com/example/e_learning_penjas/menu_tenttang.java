package com.example.e_learning_penjas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import butterknife.BindView;

public class menu_tenttang extends AppCompatActivity {
    @BindView(R.id.toolbar2)
    androidx.appcompat.widget.Toolbar Toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_tenttang);


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_tenttang.this, menu_utama.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
