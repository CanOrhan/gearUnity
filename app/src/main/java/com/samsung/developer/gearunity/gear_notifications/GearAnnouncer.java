package com.samsung.developer.gearunity.gear_notifications;

import android.app.Application;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
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
    public final static String EXTRA_TITLE = "com.samsung.developer.gearunity.notify_gear.title";
    public final static String EXTRA_SUB_HEADER = "com.samsung.developer.gearunity.notify_gear.subheader";
    public final static String EXTRA_DESCRIPTION = "com.samsung.developer.gearunity.notify_gear.description";
    public final static String EXTRA_GO_TO = "com.samsung.developer.gearunity.notify_gear.go_to";

    SrnRichNotificationManager mRichNotificationManager; //Can't be injected - 3rd party class
    @Inject Bus mBus;
    @Inject Application mApplication;
    @Inject RichNotificationListener mRichNotificationListener;

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

        //ToDo: Use the parcelable Article instead
        final String title = intent.getStringExtra(EXTRA_TITLE);
        final String subHeader = intent.getStringExtra(EXTRA_SUB_HEADER);
        final String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        final String goTo = intent.getStringExtra(EXTRA_GO_TO);

        Srn srn = new Srn();
        try {
            srn.initialize(this);
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
                mRichNotificationManager = new SrnRichNotificationManager(mApplication);
            }

            String logMe = String.format("Posting %s - %s - %s", title, subHeader, description);
            Log.d("GearStuff", logMe);

            if(intent.getAction().equals(ACTION_NOTIFY_GEAR)){
                if (mRichNotificationManager.isConnected()) {
                    Log.d("GearStuff", "Correct");
                    mRichNotificationManager.start();
                    mRichNotificationManager.registerRichNotificationListener(mRichNotificationListener);
                    NewsEventNotification newsEventNotification = new NewsEventNotification(mApplication, title, subHeader, description, Uri.parse(goTo));
                    newsEventNotification.setAlertType(SrnRichNotification.AlertType.SOUND_AND_VIBRATION, SrnRichNotification.PopupType.NORMAL);
                    mRichNotificationManager.notify(newsEventNotification);
                } else {
                    Log.d("GearStuff", "Incorrect");
                    Notification notification = new Notification.Builder(this)
                            .setContentTitle(title)
                            .setSubText(subHeader)
                            .setContentText(description)
                            .setSmallIcon(R.drawable.ic_launcher).build();
                    SrnRichNotificationManager.setRouteCondition(notification);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0, notification);
                }
            }
        } catch (SsdkUnsupportedException e) {
            Log.d("GearStuff", e.getLocalizedMessage());
        }
    }

    public static String getAction() {
        return ACTION_NOTIFY_GEAR;
    }
}
