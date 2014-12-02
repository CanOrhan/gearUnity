package com.samsung.developer.gearunity.gear_notifications;

import android.app.Application;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.richnotification.Srn;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.SrnRichNotificationManager;
import com.samsung.developer.gearunity.R;
import com.samsung.developer.gearunity.application.RssApplication;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class GearAnnouncer extends IntentService{

    public final static String ACTION_NOTIFY_GEAR = "com.samsung.developer.gearunity.notify_gear";
    SrnRichNotificationManager mNotificationManager; //Can't be injected - 3rd party class
    @Inject Bus mBus;
    @Inject Application mApplication;

    public GearAnnouncer(){
        super("GearAnnouncer");
    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GearAnnouncer(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Srn srn = new Srn();
        try {
            // Initialize an instance of Srn.
            srn.initialize(this);
        } catch (SsdkUnsupportedException e) {
            // Error handling
        }
        Log.d("GearStuff", "Got Intent");
        if(mBus == null) {
            ((RssApplication) getApplication()).getObjectGraph().inject(this);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mBus.register(this);
                }
            });
            mNotificationManager = new SrnRichNotificationManager(mApplication);
        }
        mNotificationManager.start();

        Event event = new Event(mApplication);

        Log.d("GearStuff", "Notification manager connection is: " + mNotificationManager.isConnected());
        if(intent.getAction().equals(ACTION_NOTIFY_GEAR)){
            if (mNotificationManager.isConnected()) {
                mNotificationManager.notify(event.createRichNotification());
            } else {
                Notification notification = new Notification.Builder(this)
                        .setContentTitle("New mail from " + "test@gmail.com")
                        .setContentText("Subject")
                        .setSmallIcon(R.drawable.ic_launcher).build();
                SrnRichNotificationManager.setRouteCondition(notification);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notification);
            }
        }
    }

    public static String getAction() {
        return ACTION_NOTIFY_GEAR;
    }
}
