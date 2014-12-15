package com.samsung.developer.gearunity.rss_list;

import android.view.View;
import android.widget.TextView;

import com.samsung.developer.gearunity.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class RssListItemViewHolder {
    @InjectView(R.id.header) TextView mHeaderTextView;
    @InjectView(R.id.details) TextView mDetailsTextView;

    public RssListItemViewHolder(View v){
        ButterKnife.inject(this, v);
    }

    public void setTitle(String value){
        mHeaderTextView.setText(value);
    }

    public void setDetails(String value){
        mDetailsTextView.setText(value);
    }
}
