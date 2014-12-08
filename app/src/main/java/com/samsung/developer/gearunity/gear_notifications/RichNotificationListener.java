package com.samsung.developer.gearunity.gear_notifications;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.samsung.android.sdk.richnotification.SrnRichNotificationManager;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class RichNotificationListener implements SrnRichNotificationManager.EventListener {

    private final Application mApplication; //TODO: Inject please

    @Inject
    public RichNotificationListener(Application app){
        mApplication = app;
    }


    @Override
    public void onError(UUID arg0, SrnRichNotificationManager.ErrorType arg1) {
        Toast.makeText(mApplication,
                "Something wrong with uuid" + arg0.toString() + "Error:" + arg1.toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRead(UUID arg0) {
        Toast.makeText(mApplication, "Read uuid" + arg0.toString(), Toast.LENGTH_LONG)
                .show();

    }

    @Override
    public void onRemoved(UUID arg0) {
        Toast.makeText(mApplication, "Removed uuid" + arg0.toString(), Toast.LENGTH_LONG)
                .show();

    }
}
