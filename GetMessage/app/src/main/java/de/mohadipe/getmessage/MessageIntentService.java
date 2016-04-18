package de.mohadipe.getmessage;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class MessageIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;

    public MessageIntentService() {
        super("MessageIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        if (!extras.isEmpty()) {
            String message = extras.getString("NEW_MESSAGE_RECEIVED", "unknown");

            sendNotification("Received: " + message);
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        MessageBroadcastReceiver.completeWakefulIntent(intent);
    }

    // HOWTO Put the message into a notification and post it.
    //       This is just one simple example of what you might choose to do with
    //       a GCM message.
    private void sendNotification(String msg) {

        // HOWTO access NotificationManager to send notification
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        // HOWTO create pending intent for activating PositionOverviewActivity
        //       when clicking the notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, GetMessageActivity.class), 0);

        // HOWTO build notification with the help of the NotificationCompat.Builder
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_full_open_on_phone)
                        .setContentTitle("Message Notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                        .setNumber(3);

        // HOWTO add pending intent to builder
        mBuilder.setContentIntent(contentIntent);

        // HOWTO "notify" android via NotificationManager (and builder.build())
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
