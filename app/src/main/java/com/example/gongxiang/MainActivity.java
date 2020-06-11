package com.example.gongxiang;


import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.gongxiang.Home.FrHome;
import com.example.gongxiang.OtherShare.FrQixiang;
import com.example.gongxiang.Users.FrUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frame_Layout;
    @BindView(R.id.rb_home)
    RadioButton rbhome;
    @BindView(R.id.rb_qixiang)
    RadioButton rbqixiang;
    @BindView(R.id.rb_user)
    RadioButton rbuser;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private FrHome frHome;
    private FrQixiang frQixiang;
    private FrUser frUser;
    private ArrayList<BaseActivity> fragments;
    private int position;
    private Fragment tempFragment;

    String str = "";
    String uername = "";
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            uername = bundle.getString("username");
            str = "欢迎" + uername + ",你好!";
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }
        Unbinder bind = ButterKnife.bind(this);
        rgMain=findViewById(R.id.rg_main);
        initFragment();
        initListener();
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        frHome=new FrHome();
        frQixiang=new FrQixiang();
        frUser=new FrUser();
        fragments.add(frHome);
        fragments.add(frQixiang);
        fragments.add(frUser);
    }
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_qixiang:
                        position = 1;
                        break;
                    case R.id.rb_user:
                        position = 2;
                        break;
                }
                BaseActivity baseActivity = getFragment(position);
                switchFragment(tempFragment, baseActivity);
            }
        });
        rgMain.check(R.id.rb_home);
    }


    /**
     *
     * @param position
     * @return
     */
    private BaseActivity getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseActivity baseActivity = fragments.get(position);
            return baseActivity;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseActivity nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1000) {
            Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
    }

}
