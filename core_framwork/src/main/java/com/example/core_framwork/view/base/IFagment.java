package com.example.core_framwork.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.core_framwork.presenter.IPresenter;

/**
 * Created by DELL on 2018/1/4.
 */

public interface IFagment<P extends IPresenter> {
    void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    int getLayoutId();

    P obtainPresenter();

    boolean getAttachToRoot();

    boolean useEventBus();

}
