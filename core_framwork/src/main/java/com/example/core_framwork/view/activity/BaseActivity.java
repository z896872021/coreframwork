package com.example.core_framwork.view.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.core_framwork.presenter.IPresenter;
import com.example.core_framwork.view.base.IActivity;
import com.example.core_framwork.view.istack.ActivityStack;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by DELL on 2017/12/18.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {
}
