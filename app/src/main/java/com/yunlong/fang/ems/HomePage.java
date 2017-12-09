package com.yunlong.fang.ems;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //三个Tab对应的布局
    private LinearLayout mTabHome;
    private LinearLayout mTabFunction;
    private LinearLayout mTabMe;

    //三个Tab对应的ImageButton
    private ImageButton mImgHome;
    private ImageButton mImgFunction;
    private ImageButton mImgMe;

    private TextView mHome;
    private TextView mFunction;
    private TextView mMe;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
        selectTab(0);

    }

    private void initViews() {
        mViewPager = (ViewPager)findViewById(R.id.id_viewpage);

        mTabHome = (LinearLayout)findViewById(R.id.id_tab_home);
        mTabFunction = (LinearLayout)findViewById(R.id.id_tab_function);
        mTabMe = (LinearLayout)findViewById(R.id.id_tab_me);

        mImgHome = (ImageButton)findViewById(R.id.id_tab_home_img);
        mImgFunction = (ImageButton)findViewById(R.id.id_tab_function_img);
        mImgMe = (ImageButton)findViewById(R.id.id_tab_me_img);

        mHome = (TextView)findViewById(R.id.home);
        mFunction = (TextView)findViewById(R.id.function);
        mMe = (TextView)findViewById(R.id.me);
    }

    private void initEvents() {
        mTabHome.setOnClickListener(onClickListener);
        mTabFunction.setOnClickListener(onClickListener);
        mTabMe.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resetImgs();
            switch (view.getId()){
                case R.id.id_tab_home:
                    selectTab(0);
                    break;
                case R.id.id_tab_function:
                    selectTab(1);
                    break;
                case R.id.id_tab_me:
                    selectTab(2);
                    break;
            }
        }
    };
    private void initDatas() {
        mFragments = new ArrayList<>();
        //将三个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new FunctionFragment());
        mFragments.add(new MeFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        //设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void selectTab(int i) {
        switch (i){
            case 0:
                mImgHome.setImageResource(R.mipmap.home_press);
                mHome.setTextColor(Color.rgb(26,250,41));
                break;
            case 1:
                mImgFunction.setImageResource(R.mipmap.function_press);
                mFunction.setTextColor(Color.rgb(26,250,41));
                break;
            case 2:
                mImgMe.setImageResource(R.mipmap.me_press);
                mMe.setTextColor(Color.rgb(26,250,41));
                break;
        }
        //设置当前单击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }
    private void resetImgs(){
        mImgHome.setImageResource(R.mipmap.home);
        mImgFunction.setImageResource(R.mipmap.function);
        mImgMe.setImageResource(R.mipmap.me);

        mHome.setTextColor(Color.WHITE);
        mFunction.setTextColor(Color.WHITE);
        mMe.setTextColor(Color.WHITE);
    }}
