package com.samsung.developer.gearunity.rss_list;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pkmmte.pkrss.Article;
import com.samsung.developer.gearunity.R;
import com.samsung.developer.gearunity.gear_notifications.GearAnnouncer;
import com.samsung.developer.gearunity.rss_service.bus.RssFeedUpdateEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import com.samsung.developer.gearunity.application.RssApplication;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RssListFragment extends Fragment {
    @Inject Bus mBus;
    @InjectView(R.id.article_list) ListView mArticleList;
    RssListAdapter adapter;

    private AdapterView.OnItemClickListener mArticleClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(GearAnnouncer.getAction());
            Article item = adapter.getItem(position);
            intent.putExtra(GearAnnouncer.EXTRA_TITLE, item.getTitle());
            intent.putExtra(GearAnnouncer.EXTRA_SUB_HEADER, item.getContent());
            intent.putExtra(GearAnnouncer.EXTRA_DESCRIPTION, item.getDescription());
            getActivity().startService(intent);
        }
    };

    public RssListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RssApplication)getActivity().getApplication()).getObjectGraph().inject(this);
        mBus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rss_list, container, false);
        ButterKnife.inject(this, rootView);
        mArticleList.setOnItemClickListener(mArticleClick);
        adapter = new RssListAdapter(getActivity());
        mArticleList.setAdapter(adapter);
        return rootView;
    }

    @Subscribe public void onFeedUpdateEvent(RssFeedUpdateEvent event){
        Article[] articles = event.getArticles().toArray(new Article[]{});
        adapter.setData(articles);
    }

}
