package com.example.core_framwork.view.istack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.example.core_framwork.CoreConfig;

import java.util.Stack;

public class ActivityStack extends Stack<Activity> {


    public void finishCurrentActivity() {
        Activity activity = pop();
        activity.finish();
    }

    public Activity currentActivity() {
        return lastElement();
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getClass().equals(cls)) {
                finishActivity(get(i));
            }
        }
    }

    public void finishAllActivity() {
        for (int i = 0; i < size(); i++) {
            if (get(i) != null) {
                get(i).finish();
            }
        }
        clear();
    }

    public void AppExit() {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) CoreConfig.getInstance().getContextInConfig()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(CoreConfig.getInstance().getContextInConfig().getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ActivityStack() {
    }

    private static class SingletonInstance {
        private static final ActivityStack INSTANCE = new ActivityStack();
    }

    public static ActivityStack getInstance() {
        return ActivityStack.SingletonInstance.INSTANCE;
    }
}
