package com.example.core_framwork;

import android.content.Context;

/**
 * create Config of Core
 */
public class CoreConfig {
    private Context context;

    /**
     * on / off  debug case
     */
    private boolean isDebug;

    /**
     * use in Application
     */
    private CoreConfig init(Context context) {
        this.context = context;
        return this;
    }

    public Context getContextInConfig() {
        return context;
    }

    private CoreConfig() {
    }

    private static class SingletonInstance {
        private static final CoreConfig INSTANCE = new CoreConfig();
    }

    public static CoreConfig getInstance() {
        return SingletonInstance.INSTANCE;
    }
}