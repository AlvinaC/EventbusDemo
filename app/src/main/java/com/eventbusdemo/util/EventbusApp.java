package com.eventbusdemo.util;

import android.app.Application;

import com.eventbusdemo.rxbus.RxBus;

/**
 * Created by alvina.rasquinha on 28/03/18.
 */

public class EventbusApp extends Application {
    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
    }

    public RxBus bus() {
        return bus;
    }

}
