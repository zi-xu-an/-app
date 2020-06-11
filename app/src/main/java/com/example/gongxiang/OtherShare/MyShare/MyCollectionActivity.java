package com.example.gongxiang.OtherShare.MyShare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gongxiang.OtherShare.Adapter.MyCollectionAdapter;
import com.example.gongxiang.R;
import com.example.gongxiang.bean.Collection;
import com.example.gongxiang.util.MyCollectionDbHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的收藏Activity类
 */
public class MyCollectionActivity extends AppCompatActivity {

    ListView lvMyCollection;
    List<Collection> myCollections = new ArrayList<>();
    TextView tvStuId;
    ImageButton tvBack;
    Button tvRefresh;

    MyCollectionDbHelper dbHelper;
    //CommodityDbHelper commodityDbHelper;
    MyCollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        findview();
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvStuId.setText(this.getIntent().getStringExtra("user_number"));

        dbHelper = new MyCollectionDbHelper(getApplicationContext(),MyCollectionDbHelper.DB_NAME,null,1);
        myCollections = dbHelper.readMyCollections(tvStuId.getText().toString());
        adapter = new MyCollectionAdapter(getApplicationContext());
        adapter.setData(myCollections);
        lvMyCollection.setAdapter(adapter);
        //设置长按删除事件
        lvMyCollection.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyCollectionActivity.this);
                builder.setTitle("提示:").setMessage("确定删除此收藏商品吗?").setIcon(R.drawable.icon_user).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Collection collection = (Collection) adapter.getItem(position);
                        //删除收藏商品项
                        dbHelper.deleteMyCollection(collection.getTitle(),collection.getDescription(),collection.getPrice());
                        Toast.makeText(MyCollectionActivity.this,"删除成功!",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                return false;
            }
        });
        //页面刷新

        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCollections = dbHelper.readMyCollections(tvStuId.getText().toString());
                adapter.setData(myCollections);
                lvMyCollection.setAdapter(adapter);
            }
        });
    }
    private void findview(){
        tvBack = findViewById(R.id.tv_back);
        tvStuId = findViewById(R.id.tv_stuId);
        lvMyCollection = findViewById(R.id.lv_my_collection);
        tvRefresh = findViewById(R.id.tv_refresh);
    }
}
