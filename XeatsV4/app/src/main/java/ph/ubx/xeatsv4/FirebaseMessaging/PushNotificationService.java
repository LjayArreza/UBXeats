package ph.ubx.xeatsv4.FirebaseMessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ph.ubx.xeatsv4.MainActivity;
import ph.ubx.xeatsv4.MainMenu;
import ph.ubx.xeatsv4.R;

public class PushNotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size()>0) {
            showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
        }

        if(remoteMessage.getNotification() != null) {
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

    }

    private RemoteViews getCustomNotificationDesign(String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.notif_title, title);
        remoteViews.setTextViewText(R.id.notif_message, message);
        remoteViews.setImageViewResource(R.id.notif_icon, R.drawable.logo);
        return remoteViews;
    }

    private void showNotification(String title, String message) {

        Intent intent = new Intent(this, MainActivity.class);
        String channel_id = "ubxeats_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.logo)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder = builder.setContent(getCustomNotificationDesign(title,message));
        } else  {
            builder = builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.logo);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "ubxeats", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0,builder.build());
    }

}

// create FCM for handle notification
// method for display notification with title and message
// check message data if it contains data. Then display the notification by getting title and message key from data
// get the title and message from data and show the notification
// create custom design for notification
// fitch the notification title and notification body then display the notification
// create a intent which open the page when clicking the notification
// create intent for clearing the previous page activity
// create a channel id for android version greater than or equal to oreo then the android 8 notification will be handled by the channel
// accessing device notification sound path
// creating an statement if android version is smaller than JellyBean it will show default notification else it will show the custom notification
// create notification manages for displaying notification
// create statement that if android version is greater than or equal to oreo the notification will be handled by the channel
// sending notification via php file or sending notification to single device or topics
