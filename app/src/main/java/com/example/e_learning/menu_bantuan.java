package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class menu_bantuan extends AppCompatActivity {
    @BindView(R.id.toolbar4)
    androidx.appcompat.widget.Toolbar Toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bantuan);

        ButterKnife.bind(this);
        setSupportActionBar(Toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_bantuan.this, menu_utama.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
