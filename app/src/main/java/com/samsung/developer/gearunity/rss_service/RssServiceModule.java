package com.samsung.developer.gearunity.rss_service;

import dagger.Module;
import dagger.Provides;
import com.samsung.developer.gearunity.BuildConfig;
import com.samsung.developer.gearunity.RssActivity;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
@Module(
        complete = false,
        library = true,
        injects = {
                BootReceiver.class,
                RssActivity.class
        }
)
public class RssServiceModule {
    @Provides AbstractRssIntentService providesRssService(){
        AbstractRssIntentService rssService;

        if(BuildConfig.DEBUG){
            //TODO: Make mock RSS service and inject it during debug
            rssService = new BbcRssService();
        } else {
            rssService = new BbcRssService();
        }

        return rssService;
    }
}
