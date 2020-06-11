package com.example.gongxiang.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;

public class Balance extends AppCompatActivity {

    ImageButton baBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_balance);
        findview();
        baBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findview(){
        baBack = findViewById(R.id.balance_back);
    }
}