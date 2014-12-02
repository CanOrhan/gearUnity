package com.samsung.developer.gearunity.rss_service;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.pkmmte.pkrss.Article;
import com.pkmmte.pkrss.Callback;
import com.pkmmte.pkrss.PkRSS;
import com.samsung.developer.gearunity.application.RssApplication;
import com.samsung.developer.gearunity.rss_service.bus.DownloadingRssFeedEvent;
import com.samsung.developer.gearunity.rss_service.bus.RssFeedUpdateEvent;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

public class BbcRssService extends AbstractRssIntentService {
    //TODO: Implement alarm

    @Inject Bus mBus;
    private static final String ACTION_REQUEST_FEED = "com.samsung.developer.gearunity.rss_service.bbc_request";
    private static final String BBC_RSS_URL = "http://feeds.bbci.co.uk/news/technology/rss.xml";

    private Callback mRssCallback = new Callback() {
        @Override
        public void OnPreLoad() {
            Log.d("GearStuff", "Loading...");
            //TODO: Generify
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mBus.post(new DownloadingRssFeedEvent());
                }
            });
        }

        @Override
        public void OnLoaded(final List<Article> articles) {
            Log.d("GearStuff", "Posting update...");
            //TODO: Generify
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mBus.post(new RssFeedUpdateEvent(articles));
                }
            });
        }

        @Override
        public void OnLoadFailed() {

        }
    };

    public BbcRssService() {
        super(ACTION_REQUEST_FEED);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("GearStuff", "Handling intent");
        if(mBus == null){ //TODO: Make a SpecialSamsungBus
            ((RssApplication)getApplication()).getObjectGraph().inject(this);
            //TODO: Generify
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mBus.register(this);
                }
            });
        }
        PkRSS.with(this).load(BBC_RSS_URL).nextPage().callback(mRssCallback).async();
    }

    @Override
    public String getIntentActionString() {
        return ACTION_REQUEST_FEED;
    }

}
