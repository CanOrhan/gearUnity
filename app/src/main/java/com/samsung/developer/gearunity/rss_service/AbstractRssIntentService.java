package com.samsung.developer.gearunity.rss_service;

import android.app.IntentService;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public abstract class AbstractRssIntentService extends IntentService implements IRssService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AbstractRssIntentService(String name) {
        super(name);
    }


    abstract public String getIntentActionString();
}
