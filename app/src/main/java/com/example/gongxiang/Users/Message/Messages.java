package com.example.gongxiang.Users.Message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;

public class Messages extends AppCompatActivity implements View.OnClickListener {
    //通知信息页面

    private LinearLayout fuwu;
    private LinearLayout jingpin;
    private LinearLayout qishare;
    private LinearLayout lvshare;
    private LinearLayout wuliu;
    ImageButton messages_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        findView();
    }
    private void findView(){
        jingpin=findViewById(R.id.jingpin);
        qishare=findViewById(R.id.qishare);
        lvshare=findViewById(R.id.lvshare);
        wuliu=findViewById(R.id.wuliu);
        messages_back=findViewById(R.id.messages_back);

        wuliu.setOnClickListener(this);
        lvshare.setOnClickListener(this);
        qishare.setOnClickListener(this);
        jingpin.setOnClickListener(this);
        messages_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wuliu:
                break;
            case R.id.lvshare:
                break;
            case R.id.qishare:
                break;
            case R.id.jingpin:
                break;
            case R.id.messages_back:
                finish();
                break;
        }
    }
}

