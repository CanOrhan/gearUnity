package com.samsung.developer.gearunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.samsung.developer.gearunity.application.RssApplication;
import com.samsung.developer.gearunity.rss_list.RssListFragment;
import com.samsung.developer.gearunity.rss_service.AbstractRssIntentService;
import com.samsung.developer.gearunity.rss_service.bus.DownloadingRssFeedEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class RssActivity extends Activity {

    @Inject Bus mBus;
    @Inject AbstractRssIntentService mRssService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_list);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RssListFragment())
                        .commit();
        }

        ((RssApplication)getApplication()).getObjectGraph().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(mRssService.getIntentActionString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rss_list, menu);
        return true;
    }

    @Subscribe public void onLoadingRssFeed(DownloadingRssFeedEvent event){
        //TODO: Update the refresh menu-button
        Log.d("GearStuff", "Downloading RSS feeds!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("GearStuff", "Requesting feed!");
            startService(new Intent(mRssService.getIntentActionString()));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
