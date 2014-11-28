package com.samsung.developer.gearunity.rss_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.samsung.developer.gearunity.application.RssApplication;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class BootReceiver extends BroadcastReceiver {

    public static String ACTION_BOOT_RECEIVER = "com.samsung.developer.gearunity.boot_receiver";

    private ObjectGraph mObjectGraph;
    @Inject AbstractRssIntentService mRssService;

    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_BOOT_RECEIVER)){
            Log.d("GearStuff", "On Boot");
            if(mObjectGraph == null){
                mObjectGraph = ObjectGraph.create(new RssServiceModule());
                mObjectGraph.inject(this);
            }
            context.startService(new Intent(mRssService.getIntentActionString()));
        }
    }
}
