package com.example.gongxiang.Home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.gongxiang.BaseActivity;
import com.example.gongxiang.Home.Chat.ChatActivity;
import com.example.gongxiang.Home.WuLiu.WuliuActivity;
import com.example.gongxiang.R;

public class FrHome extends BaseActivity implements View.OnClickListener{

    private ImageButton scan,money,search,wuliu,call;
    private ImageView advertise;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home, null);
        findview(view);
        return view;
    }
    private void findview(View view){
        scan=(ImageButton)view.findViewById(R.id.scanIB);
        scan.setOnClickListener(this);
        money=(ImageButton)view.findViewById(R.id.balanceIB);
        money.setOnClickListener(this);
        search=(ImageButton)view.findViewById(R.id.sqIB);
        search.setOnClickListener(this);
        wuliu=(ImageButton)view.findViewById(R.id.elIB);
        wuliu.setOnClickListener(this);
        call=(ImageButton)view.findViewById(R.id.online_service_IB);
        call.setOnClickListener(this);
        advertise=(ImageView)view.findViewById(R.id.image_view);
        advertise.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        boolean isChanged = false;
        switch (view.getId()) {
            case R.id.scanIB://一键归还
                Intent intent1 = new Intent(mContext, Lease.class);
                startActivity(intent1);
                break;
            case R.id.balanceIB://我的余额
                Intent intent2 = new Intent(mContext, Balance.class);
                startActivity(intent2);
                break;
            case R.id.sqIB://站点查询
                Intent intent3 = new Intent(mContext, Site.class);
                startActivity(intent3);
                break;
            case R.id.elIB://快递物流
                Intent intent4 = new Intent(mContext, WuliuActivity.class);
                startActivity(intent4);
                break;
            case R.id.online_service_IB://在线客服
                Intent intent5 = new Intent(mContext, ChatActivity.class);
                startActivity(intent5);
                break;
            case R.id.image_view:
                if(view == advertise)
                {
                    if(isChanged){
                        advertise.setImageDrawable(getResources().getDrawable(R.drawable.image2));
                    }else
                    {
                        advertise.setImageDrawable(getResources().getDrawable(R.drawable.image1));
                    }
                    isChanged = !isChanged;

                }
                break;}
    }
}
