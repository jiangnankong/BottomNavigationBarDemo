package com.example.jialian.bottomnavigationbardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import java.util.List;

/**
 * MainActivity的片段管理,关联bottomNavigationBar的点击
 * */

public class FragmentUtils {

    private FragmentManager fragmentManager;
    private List<Fragment> list;
    private BottomNavigationBar mBottomNavigationBar;
    private int layout;
    private int currentIndex;

    public static FragmentUtils getInstance(FragmentManager supportFragmentManager, List<Fragment> mFragmentList, int fragment_list, BottomNavigationBar bottomNavigationBar) {
        return new FragmentUtils(supportFragmentManager,mFragmentList,fragment_list,bottomNavigationBar);
    }

    public FragmentUtils(FragmentManager supportFragmentManager, List<Fragment> mFragmentList, int fragment_list, BottomNavigationBar bottomNavigationBar) {
        this.fragmentManager = supportFragmentManager;
        this.list = mFragmentList;
        this.mBottomNavigationBar = bottomNavigationBar;
        this.layout = fragment_list;

        showFragment(0);
    }


    //显示fragment
    private void showFragment(int position) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = list.get(position);
        if (!fragment.isAdded()) {
            ft.add(layout, fragment);
        }
        ft.show(fragment);
        if (currentIndex != position){
        ft.hide(list.get(currentIndex));
            currentIndex = position;
        }
        ft.commit();
    }

    public void getFragment() {
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        showFragment(0);
                        break;
                    case 1:
                        showFragment(1);
                        break;
                    case 2:
                        showFragment(2);
                        break;
                    case 3:
                        showFragment(3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }


}
