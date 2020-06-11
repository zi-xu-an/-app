package com.example.gongxiang.OtherShare;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.R;
import com.example.gongxiang.OtherShare.Adapter.AllCommodityAdapter;
import com.example.gongxiang.bean.Commodity;
import com.example.gongxiang.util.CommodityDbHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * 不同类型商品信息的活动类
 */
public class CommodityTypeActivity extends AppCompatActivity {

    TextView tvCommodityType;
    ListView lvCommodityType;
    List<Commodity> commodities = new LinkedList<>();

    CommodityDbHelper dbHelper;
    AllCommodityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_type);
        //返回事件
        ImageButton tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvCommodityType = findViewById(R.id.tv_type);
        lvCommodityType = findViewById(R.id.list_commodity);
        dbHelper = new CommodityDbHelper(getApplicationContext(),CommodityDbHelper.DB_NAME,null,1);
        adapter = new AllCommodityAdapter(getApplicationContext());
        //根据不同的状态显示不同的界面
        int status = this.getIntent().getIntExtra("status",0);
        if(status == 1) {
            tvCommodityType.setText("书籍");
        }else if(status == 2) {
            tvCommodityType.setText("电子产品");
        }else if(status == 3) {
            tvCommodityType.setText("运动器材");
        }else if(status == 4) {
            tvCommodityType.setText("雨伞");
        }else if(status == 5) {
            tvCommodityType.setText("家电");
        }else if(status == 6) {
            tvCommodityType.setText("衣服包包");
        }else if(status == 7) {
            tvCommodityType.setText("交通工具");
        }else if(status == 8) {
            tvCommodityType.setText("宠物");
        }else if(status == 9) {
            tvCommodityType.setText("其他闲置");
        }

        //根据不同类别显示不同的商品信息
        commodities = dbHelper.readCommodityType(tvCommodityType.getText().toString());
        adapter.setData(commodities);
        lvCommodityType.setAdapter(adapter);
    }
}
