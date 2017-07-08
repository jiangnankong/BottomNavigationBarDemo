package com.example.jialian.bottomnavigationbardemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jialian.bottomnavigationbardemo.observable.EventBadgeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private BottomNavigationBar mBottomNavigationBar;
    private BadgeItem mBadgeItem;
    private BadgeItem mBadgeItem2;
    private int num;
    private ImageView mIconView;
    private ImageView mIconView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addBtn = (Button) findViewById(R.id.btn_badge_add);
        Button reduceBtn = (Button) findViewById(R.id.btn_badge_reduce);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBadgeNum(num++);
            }
        });
        reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBadgeNum(num--);
            }
        });
        initTab();
        initFragment();
    }

    /**
     * 初识化tab
     */
    private void initTab() {
        EventBadgeItem.getInstance().register(this);

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setInActiveColor(R.color.line_bg_color);
        mBottomNavigationBar.setActiveColor(R.color.mainColor);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);
        mBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setAnimationDuration(200)
                .setBackgroundColorResource(R.color.red)
                .setHideOnSelect(false)
                .setText("0");
        mBadgeItem2 = new BadgeItem()
                .setBorderWidth(4)
                .setAnimationDuration(200)
                .setBackgroundColorResource(R.color.red)
                .setHideOnSelect(true)
                .setText("0");

        List<BottomNavigationItem> items = new ArrayList<>();
        items.add(getItem(R.mipmap.icon_home, R.string.main_home));
        items.add(getItem(R.mipmap.icon_type, R.string.main_type));
        items.add(getItem(R.mipmap.icon_sc, R.string.main_shop_car).setBadgeItem(mBadgeItem));
        items.add(getItem(R.mipmap.icon_mine, R.string.main_mine).setBadgeItem(mBadgeItem2));

        for (BottomNavigationItem item : items) {
            mBottomNavigationBar.addItem(item);
        }
        mBottomNavigationBar.setFirstSelectedPosition(0);
        mBottomNavigationBar.initialise();


        //获取 bar 的 所对应的子 view 控件，方便扩展动画
        LayoutInflater inflater = LayoutInflater.from(this);
        View parentView = inflater.inflate(com.ashokvarma.bottomnavigation.R.layout.bottom_navigation_bar_container, mBottomNavigationBar, true);
        LinearLayout mTabContainer = (LinearLayout) parentView.findViewById(com.ashokvarma.bottomnavigation.R.id.bottom_navigation_bar_item_container);
        //购物车标签是对应位置是 mTabContainer 的2
        mIconView = (ImageView) mTabContainer.getChildAt(2).findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
        mIconView2 = (ImageView) mTabContainer.getChildAt(3).findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
    }


    /**
     * 初始化对应的fragment
     */
    private void initFragment() {

        //tab 和 fragment 联动
        List<Fragment> mFragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mFragmentList.add(ItemFragment.newInstance(i + ""));
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentUtils.getInstance(supportFragmentManager, mFragmentList, R.id.fragment_list, mBottomNavigationBar).getFragment();
    }

    private BottomNavigationItem getItem(int icon, int string) {
        return new BottomNavigationItem(icon, string);
    }


    /**
     * 设置tab数字提示加缩放动画
     */
    private void setBadgeNum(int num) {
        mBadgeItem.setText(String.valueOf(num));
        mBadgeItem2.setText(String.valueOf(num));
        if (num == 5) {
            mBadgeItem.hide();
            mBadgeItem2.hide();
        } else {
            mBadgeItem.show();
            mBadgeItem2.show();
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_view_badge);
            ObjectAnimator.ofFloat(mIconView, "translationX", 2000, 0).start();
            mIconView2.startAnimation(animation);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            mBadgeItem.setText((String) arg);
            mBadgeItem2.setText((String) arg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBadgeItem.getInstance().unregister(this);
    }
}
