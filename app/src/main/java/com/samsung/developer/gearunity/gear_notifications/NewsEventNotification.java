package com.samsung.developer.gearunity.gear_notifications;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.format.Time;

import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.actions.SrnHostAction;
import com.samsung.android.sdk.richnotification.templates.SrnPrimaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnStandardSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnStandardTemplate;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class NewsEventNotification extends SrnRichNotification{

    @Inject Application mApplication;

    public NewsEventNotification(Application application, String title, String subHeader, String article, Uri goTo){
        super(application);
        mApplication = application;

        setTitle(title);
        setPrimaryTemplate(getSmallPrimaryTemplate(subHeader));
        setSecondaryTemplate(getSmallSecondaryTemplate(title, subHeader, article));
        addActions(generateActions(goTo));

    }

    private static SrnPrimaryTemplate getSmallPrimaryTemplate(String subHeader){
        SrnStandardTemplate smlTeplate = new SrnStandardTemplate(SrnStandardTemplate.HeaderSizeType.SMALL);

        smlTeplate.setSubHeader(subHeader);
        smlTeplate.setBackgroundColor(Color.rgb(0, 0, 255));

        return smlTeplate;
    }

    private SrnSecondaryTemplate getSmallSecondaryTemplate(String title, String subHeader, String article) {
        SrnStandardSecondaryTemplate smallSecTemplate = new SrnStandardSecondaryTemplate();

        smallSecTemplate.setTitle(title);
        smallSecTemplate.setSubHeader(subHeader);
        smallSecTemplate.setBody(article);

        return smallSecTemplate;
    }

    private ArrayList<SrnAction> generateActions(Uri goTo){
        ArrayList<SrnAction> actions = new ArrayList<SrnAction>();

        //Go to website
        SrnHostAction launchBbcWebsiteAction = new SrnHostAction("Read on phone");
        Intent bbcWebsiteIntent = new Intent(Intent.ACTION_VIEW, goTo);
        launchBbcWebsiteAction.setCallbackIntent(SrnAction.CallbackIntent.getActivityCallback(bbcWebsiteIntent));

        //Share
        SrnHostAction launchShareChooser = new SrnHostAction("Share");
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Have you seen this? " + goTo.toString());
        shareIntent.setType("text/plain");
        launchShareChooser.setCallbackIntent(SrnAction.CallbackIntent.getActivityCallback(shareIntent));

        //Add all actions
        actions.add(launchBbcWebsiteAction);
        actions.add(launchShareChooser);

        return actions;
    }


}
