package com.example.gongxiang.OtherShare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.gongxiang.BaseActivity;
import com.example.gongxiang.OtherShare.MyShare.AddCommodityActivity;
import com.example.gongxiang.OtherShare.MyShare.Allgoods;
import com.example.gongxiang.OtherShare.MyShare.MyCollectionActivity;
import com.example.gongxiang.OtherShare.MyShare.MyCommodityActivity;
import com.example.gongxiang.R;
import com.example.gongxiang.util.Constant;

public class FrQixiang extends BaseActivity implements View.OnClickListener{

    private ImageButton book,ep,se,umbrella,he,cb,vehicle,pets,other,mygoods,addproduct,mylove,all;
    String username = Constant.username;

    @Override
    public View initView(){
        View view = View.inflate(mContext, R.layout.qixiang, null);
        findview(view);
        return view;
    }

    private void findview(View view){
        book=(ImageButton)view.findViewById(R.id.bIB);
        book.setOnClickListener(this);
        ep=(ImageButton)view.findViewById(R.id.eIB);
        ep.setOnClickListener(this);
        se=(ImageButton)view.findViewById(R.id.seIB);
        se.setOnClickListener(this);
        umbrella=(ImageButton)view.findViewById(R.id.uIB);
        umbrella.setOnClickListener(this);
        he=(ImageButton)view.findViewById(R.id.heIB);
        he.setOnClickListener(this);
        cb=(ImageButton)view.findViewById(R.id.cbIB);
        cb.setOnClickListener(this);
        vehicle=(ImageButton)view.findViewById(R.id.vIB);
        vehicle.setOnClickListener(this);
        pets=(ImageButton)view.findViewById(R.id.pIB);
        pets.setOnClickListener(this);
        other=(ImageButton)view.findViewById(R.id.oIB);
        other.setOnClickListener(this);
        mygoods=(ImageButton)view.findViewById(R.id.btn_my_goods);
        mygoods.setOnClickListener(this);
        addproduct=(ImageButton)view.findViewById(R.id.ib_add_product);
        addproduct.setOnClickListener(this);
        mylove=(ImageButton)view.findViewById(R.id.btn_my_collection);
        mylove.setOnClickListener(this);
        all=(ImageButton)view.findViewById(R.id.all_IB);
        all.setOnClickListener(this);
    }
    final Bundle bundle2 = new Bundle();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bIB://书籍
                bundle2.putInt("status",1);
                Intent intent1 = new Intent(mContext, CommodityTypeActivity.class);
                intent1.putExtras(bundle2);
                startActivity(intent1);
                break;
            case R.id.eIB://电子产品
                bundle2.putInt("status",2);
                Intent intent2 = new Intent(mContext, CommodityTypeActivity.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.seIB://运动器材
                bundle2.putInt("status",3);
                Intent intent3 = new Intent(mContext, CommodityTypeActivity.class);
                intent3.putExtras(bundle2);
                startActivity(intent3);
                break;
            case R.id.uIB://雨伞
                bundle2.putInt("status",4);
                Intent intent4 = new Intent(mContext, CommodityTypeActivity.class);
                intent4.putExtras(bundle2);
                startActivity(intent4);
                break;
            case R.id.heIB://家电
                bundle2.putInt("status",5);
                Intent intent5 = new Intent(mContext, CommodityTypeActivity.class);
                intent5.putExtras(bundle2);
                startActivity(intent5);
                break;
            case R.id.cbIB://衣服包包
                bundle2.putInt("status",6);
                Intent intent6 = new Intent(mContext, CommodityTypeActivity.class);
                intent6.putExtras(bundle2);
                startActivity(intent6);
                break;
            case R.id.vIB://交通工具
                bundle2.putInt("status",7);
                Intent intent7 = new Intent(mContext, CommodityTypeActivity.class);
                intent7.putExtras(bundle2);
                startActivity(intent7);
                break;
            case R.id.pIB://宠物
                bundle2.putInt("status",8);
                Intent intent8 = new Intent(mContext, CommodityTypeActivity.class);
                intent8.putExtras(bundle2);
                startActivity(intent8);
                break;
            case R.id.oIB://其他闲置
                bundle2.putInt("status",9);
                Intent intent9 = new Intent(mContext, CommodityTypeActivity.class);
                intent9.putExtras(bundle2);
                startActivity(intent9);
                break;
            case R.id.btn_my_goods://我的发布
                Intent intent10 = new Intent(mContext, MyCommodityActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("stu_id",username);
                intent10.putExtras(bundle1);
                startActivity(intent10);
                break;
            case R.id.ib_add_product://发布宝贝
                Intent intent11 = new Intent(mContext, AddCommodityActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("user_id",username);
                intent11.putExtras(bundle2);
                startActivity(intent11);
                break;
            case R.id.btn_my_collection://我的收藏
                Intent intent12 = new Intent(mContext, MyCollectionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_number",username);
                intent12.putExtras(bundle);
                startActivity(intent12);
                break;
            case R.id.all_IB://所有商品
                Intent intent13 = new Intent(mContext, Allgoods.class);
                startActivity(intent13);
                break;
        }
    }

}


