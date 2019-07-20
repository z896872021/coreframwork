package com.example.core_framwork.presenter;

import android.app.Activity;

public interface IPresenter {
    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 在{@link Activity#onDestroy()}中使用
     */
    void onDestroy();
}
