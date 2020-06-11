package com.example.gongxiang.Users.Question;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;

public class Question extends AppCompatActivity implements View.OnClickListener{
    //问题与反馈页面
    private Button back;
    ImageButton question_back;
    private EditText wenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        findView();
    }
    private void findView(){
        back = findViewById(R.id.back);
        wenti = findViewById(R.id.wenti);
        question_back = findViewById(R.id.question_back);

        back.setOnClickListener(this);
        question_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
            case R.id.question_back:
                finish();
                break;
        }
    }
}
