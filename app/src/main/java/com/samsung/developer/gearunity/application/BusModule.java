package com.samsung.developer.gearunity.application;

import com.samsung.developer.gearunity.RssActivity;
import com.samsung.developer.gearunity.rss_service.BbcRssService;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.samsung.developer.gearunity.GearAnnouncerService;
import com.samsung.developer.gearunity.RssListFragment;

/**
 * Created by c.orhan on 27/11/2014.
 */
@Module(
        complete = false,
        injects = {
                RssListFragment.class,
                GearAnnouncerService.class,
                RssActivity.class,
                BbcRssService.class
        }
)
public class BusModule {
    private Bus mBus;

    public BusModule(Bus bus){
        mBus = bus;
    }

    @Provides @Singleton Bus provideBus(){
        return mBus;
    }
}
