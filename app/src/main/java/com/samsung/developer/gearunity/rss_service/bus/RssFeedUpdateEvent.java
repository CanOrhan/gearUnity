package com.samsung.developer.gearunity.rss_service.bus;

import com.pkmmte.pkrss.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class RssFeedUpdateEvent {

    private ArrayList<Article> mArticles = new ArrayList<Article>();

    public RssFeedUpdateEvent(List<Article> articles) {
        mArticles.addAll(articles);
    }

    public ArrayList<Article> getArticles(){
        return mArticles;
    }
}
