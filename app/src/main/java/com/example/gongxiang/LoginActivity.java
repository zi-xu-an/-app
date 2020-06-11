package com.example.gongxiang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.bean.User;
import com.example.gongxiang.util.Constant;
import com.example.gongxiang.util.UserDbHelper;

import java.util.LinkedList;

/**
 * 登录界面Activity类
 * @author : autumn_leaf
 */
public class LoginActivity extends AppCompatActivity {

    EditText EtStuNumber,EtStuPwd;
    private String username;

    LinkedList<User> users = new LinkedList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView tvRegister = findViewById(R.id.register);
        //跳转到注册界面
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        EtStuNumber = findViewById(R.id.users);
        EtStuPwd = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                if(CheckInput()) {
                    UserDbHelper dbHelper = new UserDbHelper(getApplicationContext(), UserDbHelper.DB_NAME,null,1);
                    users = dbHelper.readUsers();
                    for(User user : users) {
                        //如果可以找到,则输出登录成功,并跳转到主界面
                        if(user.getUsername().equals(EtStuNumber.getText().toString()) && user.getPassword().equals(EtStuPwd.getText().toString()) ) {
                            flag = true;
                            Toast.makeText(LoginActivity.this,"恭喜你登录成功!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            username = EtStuNumber.getText().toString();
                            bundle.putString("username",username);
                            intent.putExtras(bundle);
                            Constant.username = username;
                            startActivity(intent);
                        }
                    }
                    //否则提示登录失败,需要重新输入
                    if (!flag) {
                        Toast.makeText(LoginActivity.this,"学号或密码输入错误!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //检查输入是否符合要求
    public boolean CheckInput() {
        String StuNumber = EtStuNumber.getText().toString();
        String StuPwd = EtStuPwd.getText().toString();
        if(StuNumber.trim().equals("")) {
            Toast.makeText(LoginActivity.this,"学号不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuPwd.trim().equals("")) {
            Toast.makeText(LoginActivity.this,"密码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
