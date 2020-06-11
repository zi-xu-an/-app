package com.example.gongxiang.Users.UseFile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;
import com.example.gongxiang.bean.Userfile;
import com.example.gongxiang.util.Constant;
import com.example.gongxiang.util.UsersfileDbHelper;

import java.util.LinkedList;

public class AlterUserFile extends AppCompatActivity implements View.OnClickListener {

    private Button alterok;
    private EditText alternicheng;
    ImageButton alteruserfile_back;
    String username = Constant.username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alteruserfile);
        Intent Alter = this.getIntent();
        findView();


        UsersfileDbHelper dbHelper = new UsersfileDbHelper(getApplicationContext(), UsersfileDbHelper.DB_NAME,null,1);
        LinkedList<Userfile> userfiles = dbHelper.readUserfile(username);
        if(userfiles != null) {
            for (Userfile userfile : userfiles) {
                alternicheng.setText(userfile.getName());
            }
        }


    }

    private void findView() {
        alternicheng=findViewById(R.id.alternicheng);
        alterok=findViewById(R.id.alterok);
        alteruserfile_back=findViewById(R.id.alteruserfile_back);

        alteruserfile_back.setOnClickListener(this);
        alterok.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alterok:
            case R.id.alteruserfile_back:
                Intent intent =getIntent();
                Bundle bundle =new Bundle();
                bundle.putString("name",alternicheng.getText().toString().trim());
                intent.putExtras(bundle);
                setResult(6,intent);
                finish();
                break;
        }
    }
}
