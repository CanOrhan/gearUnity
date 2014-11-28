package com.samsung.developer.gearunity.rss_service.bus;

import com.pkmmte.pkrss.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class RssFeedUpdateEvent {

    private ArrayList<Article> mAricles = new ArrayList<Article>();

    public RssFeedUpdateEvent(List<Article> articles) {
        mAricles.addAll(articles);
    }

    public ArrayList<Article> getArticles(){
        return mAricles;
    }
}
