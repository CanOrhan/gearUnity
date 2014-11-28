package com.samsung.developer.gearunity.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.samsung.developer.gearunity.RssActivity;
import com.samsung.developer.gearunity.RssListFragment;
import com.samsung.developer.gearunity.rss_service.BbcRssService;

/**
 * Created by c.orhan on 27/11/2014.
 */

@Module(
        library = true,
        complete = false,
        injects = {
                RssApplication.class,
                RssListFragment.class,
                RssActivity.class,
                BbcRssService.class
        }
)
public class RssApplicationModule {
    private final RssApplication mRssApplication;

    public RssApplicationModule(RssApplication rssApplication){
        this.mRssApplication = rssApplication;
    }

    @Provides @Singleton Application provideApplication(){
        return mRssApplication;
    }
}
