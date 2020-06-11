package com.example.gongxiang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.bean.User;
import com.example.gongxiang.util.UserDbHelper;

/**
 * 注册界面Activity类
 * @author : autumn_leaf
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tvStuNumber, tvStuPwd, tvStuConfirmPwd;
    private EditText mEtRegisteractivityPhonecodes;
    private ImageView mIvRegisteractivityShowcode;
    private String realCode;
    private Button btnRegister,btnCancel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initView();


        mIvRegisteractivityShowcode.setImageBitmap(RxCaptcha.getInstance().createBitmap());
        realCode = RxCaptcha.getInstance().getCode().toLowerCase();
    }

    private void initView() {
        btnRegister  = findViewById(R.id.reg);
        tvStuNumber = findViewById(R.id.account);
        tvStuPwd = findViewById(R.id.registerpassword);
        tvStuConfirmPwd = findViewById(R.id.repassword);
        mEtRegisteractivityPhonecodes = findViewById(R.id.cachp);
        mIvRegisteractivityShowcode = findViewById(R.id.cap);
        btnCancel = findViewById(R.id.btn_cancel);

        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        mIvRegisteractivityShowcode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cap:    //改变随机验证码的生成
                mIvRegisteractivityShowcode.setImageBitmap(RxCaptcha.getInstance().createBitmap());
                realCode = RxCaptcha.getInstance().getCode().toLowerCase();
                break;
            case R.id.btn_cancel:   //返回到登录界面
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.reg:    //注册按钮
                String phoneCode = mEtRegisteractivityPhonecodes.getText().toString().toLowerCase();
                if(CheckInput()&& !TextUtils.isEmpty(phoneCode)) {
                    if (phoneCode.equals(realCode)) {
                    User user = new User();
                    user.setUsername(tvStuNumber.getText().toString());
                    user.setPassword(tvStuPwd.getText().toString());
                    UserDbHelper dbHelper = new UserDbHelper(getApplicationContext(), UserDbHelper.DB_NAME,null,1);
                    dbHelper.addUser(user);
                    Toast.makeText(RegisterActivity.this,"恭喜你注册成功!",Toast.LENGTH_SHORT).show();
                    //销毁当前界面
                    finish();
                }else {
                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                        mIvRegisteractivityShowcode.setImageBitmap(RxCaptcha.getInstance().createBitmap());
                        realCode = RxCaptcha.getInstance().getCode().toLowerCase();
                    }
            }
        }

    }

    //判断输入是否符合规范
    public boolean CheckInput() {
        String username = tvStuNumber.getText().toString();
        String password = tvStuPwd.getText().toString();
        String confirm_password = tvStuConfirmPwd.getText().toString();
        if(username.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(confirm_password.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"确认密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.trim().equals(confirm_password.trim())) {
            Toast.makeText(RegisterActivity.this,"两次密码输入不一致!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}