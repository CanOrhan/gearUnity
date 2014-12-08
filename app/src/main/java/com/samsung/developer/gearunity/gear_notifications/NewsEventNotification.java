package com.samsung.developer.gearunity.gear_notifications;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.format.Time;

import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.templates.SrnPrimaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnStandardSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnStandardTemplate;

import javax.inject.Inject;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class NewsEventNotification extends SrnRichNotification{

    @Inject Application mApplication;

    public NewsEventNotification(Application application, String title, String subHeader, String article){
            super(application);
            mApplication = application;

            setTitle(title);
            setPrimaryTemplate(getSmallPrimaryTemplate(subHeader));
            setSecondaryTemplate(getSmallSecondaryTemplate(title, subHeader, article));
        }

    private static SrnPrimaryTemplate getSmallPrimaryTemplate(String subHeader){
        SrnStandardTemplate smlTeplate = new SrnStandardTemplate(SrnStandardTemplate.HeaderSizeType.SMALL);

        smlTeplate.setSubHeader(subHeader);
        smlTeplate.setBackgroundColor(Color.rgb(0, 0, 255));

        return smlTeplate;
    }
    public SrnSecondaryTemplate getSmallSecondaryTemplate(String title, String subHeader, String article) {
        SrnStandardSecondaryTemplate smallSecTemplate = new SrnStandardSecondaryTemplate();

        smallSecTemplate.setTitle(title);
        smallSecTemplate.setSubHeader(subHeader);
        smallSecTemplate.setBody(article);

        return smallSecTemplate;
    }

}
