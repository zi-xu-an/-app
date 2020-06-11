package com.example.gongxiang.Users;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.gongxiang.R;

public class ImageShower extends Activity {

    private Bitmap bitmap;
    private ImageView imagebighead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
        findview();
        Intent mIntent=getIntent();
        if (mIntent != null && mIntent.getParcelableExtra("BigHead") != null) {
            bitmap=getIntent().getParcelableExtra("BigHead");
            imagebighead.setImageBitmap(bitmap);
        }
        final ImageLoadingDialog dialog = new ImageLoadingDialog(this);
        dialog.show();
        // 1.5秒后关闭后dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    private void findview() {
        imagebighead=findViewById(R.id.imagebighead);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finish();
        return true;
    }
}
