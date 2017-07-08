package com.example.jialian.bottomnavigationbardemo.observable;

import java.util.Observable;

/**
 * 作者：Konng on 2017/6/29 20:16
 * 邮箱：197726885@qq.com
 * 说明：
 * 详细：
 */

public class BadgeItemObservable extends Observable {
    //自定义方法名
    public void postNewPublication(Object content) {
        //标识内容发生改变,必须调用，否则notify无效
        setChanged();
        //通知所有观察者
        notifyObservers(content);
    }
}
