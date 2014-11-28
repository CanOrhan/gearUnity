package com.samsung.developer.gearunity.application;

import android.app.Application;

import com.samsung.developer.gearunity.rss_service.RssServiceModule;
import com.squareup.otto.Bus;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by c.orhan on 27/11/2014.
 */
public class RssApplication extends Application {

    private ObjectGraph mApplicationObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    public ObjectGraph getObjectGraph(){
        return mApplicationObjectGraph;
    }

    protected List<Object> getModules(){
        return Arrays.asList(new RssApplicationModule(this), new BusModule(new Bus()), new RssServiceModule());
    }
}
