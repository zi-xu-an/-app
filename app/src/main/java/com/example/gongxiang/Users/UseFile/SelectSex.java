package com.example.gongxiang.Users.UseFile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;

public class SelectSex extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout man;
    private LinearLayout male;
    ImageButton selectsex_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectsex);
        findView();
    }

    private void findView() {
        man=findViewById(R.id.man);
        male=findViewById(R.id.male);
        selectsex_back=findViewById(R.id.select_back);

        man.setOnClickListener(this);
        male.setOnClickListener(this);
        selectsex_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle =new Bundle();
        Intent intent = getIntent();
        switch (v.getId()) {
            case R.id.man:
                bundle.putString("sex","男");
                intent.putExtras(bundle);
                setResult(3,intent);
                finish();
                break;
            case R.id.male:
                bundle.putString("sex","女");
                intent.putExtras(bundle);
                setResult(3,intent);
                finish();
                break;
            case R.id.select_back:
                finish();
        }
    }
}
