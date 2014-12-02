package com.samsung.developer.gearunity.gear_notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.templates.SrnPrimaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnQRSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnSecondaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnStandardTemplate;
import com.samsung.developer.gearunity.R;

/**
 * Created by Can Orhan on behalf of Samsung Electronics.
 */
public class Event {

    private final Context mContext;

    public Event(Context context){
        mContext = context;
    }

    public SrnRichNotification createRichNotification() {
        SrnRichNotification noti = new SrnRichNotification(mContext);
        noti.setReadout("New Event", "Today there is an event.");

        Bitmap appIconBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
        SrnImageAsset appIcon = new SrnImageAsset(mContext, "app_icon", appIconBitmap);
        noti.setIcon(appIcon);

        noti.setTitle("Eventify");

        noti.setPrimaryTemplate(getEventTemplate());

        noti.setSecondaryTemplate(getEventSecondaryTemplate());

        noti.setAlertType(SrnRichNotification.AlertType.SOUND_AND_VIBRATION, SrnRichNotification.PopupType.NORMAL);

        return noti;
    }

    public SrnPrimaryTemplate getEventTemplate() {
        SrnStandardTemplate mMediumHeaderTemplate = new SrnStandardTemplate(SrnStandardTemplate.HeaderSizeType.MEDIUM);
        mMediumHeaderTemplate.setBackgroundColor(Color.rgb(0, 255, 0));

        mMediumHeaderTemplate.setSubHeader("<b> Scheduled Event </b>");
        mMediumHeaderTemplate.setBody("Scheduled Meeting 10mins from now");

        return mMediumHeaderTemplate;
    }

    public SrnSecondaryTemplate getEventSecondaryTemplate() {
        SrnQRSecondaryTemplate qrSecTemplate = new SrnQRSecondaryTemplate();

        qrSecTemplate.addListItem("Attendee", "Chitra Sampath Kumar");
        qrSecTemplate.addListItem("Attendee", "Taehee Lee");
        qrSecTemplate.addListItem("Attendee", "Hunje Yun");
        qrSecTemplate.addListItem("Attendee", "Minsuk Choi");
        qrSecTemplate.addListItem("Attendee", "Jihwa Park");
        qrSecTemplate.addListItem("Attendee", "Junho Lee");

        return qrSecTemplate;
    }

}
