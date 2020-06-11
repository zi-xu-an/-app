package com.example.gongxiang.Users;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gongxiang.BaseActivity;
import com.example.gongxiang.LoginActivity;
import com.example.gongxiang.R;
import com.example.gongxiang.Users.Message.Messages;
import com.example.gongxiang.Users.Question.Question;
import com.example.gongxiang.Users.UseFile.UserFile;
import com.example.gongxiang.bean.Userfile;
import com.example.gongxiang.util.Constant;
import com.example.gongxiang.util.UsersfileDbHelper;

import java.util.LinkedList;

public class FrUser extends BaseActivity implements View.OnClickListener{

    private Button btnquit;
    private ImageView imageshead;
    private TextView uname;
    private LinearLayout userfile;
    private LinearLayout messages;
    private LinearLayout question;
    private String sex="";
    private Bitmap bitmap;
    String username = Constant.username;


    @Override
    public View initView(){
        View view = View.inflate(mContext, R.layout.users, null);
        findview(view);

        UsersfileDbHelper dbHelper = new UsersfileDbHelper(mContext.getApplicationContext(), UsersfileDbHelper.DB_NAME,null,1);
        LinkedList<Userfile> userfiles = dbHelper.readUserfile(username);
        if(userfiles != null) {
            for (Userfile userfile : userfiles) {
                uname.setText(userfile.getName());
                sex=userfile.getSex();
            }
        }

        return view;
    }

    private void findview(View view){
        uname=view.findViewById(R.id.u_name);
        imageshead=view.findViewById(R.id.images_head);
        imageshead.setOnClickListener(this);
        btnquit=(Button)view.findViewById(R.id.btn_quit);
        btnquit.setOnClickListener(this);
        userfile=(LinearLayout)view.findViewById(R.id.userfile);
        userfile.setOnClickListener(this);
        messages=(LinearLayout)view.findViewById(R.id.messages);
        messages.setOnClickListener(this);
        question=(LinearLayout)view.findViewById(R.id.question);
        question.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.images_head:
                //放大图片
//                Intent Head = new Intent(mContext, ImageShower.class);
//                imageshead.setDrawingCacheEnabled(Boolean.TRUE);
//                Head.putExtra("BigHead", imageshead.getDrawingCache());
//                startActivity(Head);
//                imageshead.setDrawingCacheEnabled(false);
                break;
            case R.id.btn_quit:
                Intent Quit = new Intent(mContext, LoginActivity.class);
                startActivity(Quit);
                break;
            case R.id.userfile:
                Intent Userfile = new Intent(mContext, UserFile.class);
//                Userfile.putExtra("name",uname.getText().toString());
//                Userfile.putExtra("sex",sex);
//                imageshead.setDrawingCacheEnabled(Boolean.TRUE);
//                Userfile.putExtra("Head", imageshead.getDrawingCache());
                startActivityForResult(Userfile,2);
//                imageshead.setDrawingCacheEnabled(false);
                break;
            case R.id.messages:
                Intent intent3 = new Intent(mContext, Messages.class);
                startActivity(intent3);
                break;
            case R.id.question:
                Intent intent4 = new Intent(mContext, Question.class);
                startActivity(intent4);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2&&resultCode==1){
            Bundle bundle=data.getExtras();
            if(bundle!=null) {
                sex=bundle.getString("xingbie");
                String name=bundle.getString("nicheng");
                uname.setText(name);
            }
            if (data != null && data.getParcelableExtra("head") != null) {
                bitmap=data.getParcelableExtra("head");
                imageshead.setImageBitmap(bitmap);
            }
        }
    }

}
