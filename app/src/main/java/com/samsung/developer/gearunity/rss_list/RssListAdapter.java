package com.samsung.developer.gearunity.rss_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pkmmte.pkrss.Article;
import com.samsung.android.sdk.richnotification.SrnRichNotificationManager;
import com.samsung.developer.gearunity.R;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class RssListAdapter extends BaseAdapter {

    private final Context mContext;
    private Article[] mItems;

    public RssListAdapter(Context context, Article[] objects) {
        mContext = context;
        mItems = objects;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article item = mItems[position];
        RssListItemViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rss_list_item, parent, false);
            viewHolder = new RssListItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RssListItemViewHolder) convertView.getTag();
        }

        viewHolder.setTitle(item.getTitle());
        viewHolder.setDetails(item.getDescription());
        return convertView;
    }
}
