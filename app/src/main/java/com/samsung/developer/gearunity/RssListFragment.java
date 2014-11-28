package com.samsung.developer.gearunity;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.developer.gearunity.rss_service.bus.RssFeedUpdateEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import com.samsung.developer.gearunity.application.RssApplication;
import com.squareup.otto.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class RssListFragment extends Fragment {
    @Inject Bus mBus;

    public RssListFragment() {
        // Required empty public constructor
    }

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
        return rootView;
    }

    @Subscribe public void onFeedUpdateEvent(RssFeedUpdateEvent event){
        Log.d("GearStuff", "Got " + event.getArticles().size() + " articles!");
    }

}
