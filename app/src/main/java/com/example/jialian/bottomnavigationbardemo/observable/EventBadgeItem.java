package com.example.jialian.bottomnavigationbardemo.observable;

import java.util.Observer;

/**
 * 作者：Konng on 2017/6/30 10:44
 * 邮箱：197726885@qq.com
 * 说明：
 * 详细：
 */

public class EventBadgeItem {

    private static EventBadgeItem sInstance;
    private BadgeItemObservable observable;

    //双锁单例
    public static EventBadgeItem getInstance() {
        if (sInstance == null) {
            synchronized (EventBadgeItem.class) {
                if (sInstance == null) {
                    sInstance = new EventBadgeItem();
                }
            }
        }
        return sInstance;
    }

    private EventBadgeItem() {
        observable = new BadgeItemObservable();
    }
    //添加 observer 到 observable中
    public void register(Observer o) {
        observable.addObserver(o);
    }

    public void unregister(Observer o) {
        observable.deleteObserver(o);
    }
    //发送更新数据给 observer
    public void post(Object obj) {
        observable.postNewPublication(obj);
    }
}
