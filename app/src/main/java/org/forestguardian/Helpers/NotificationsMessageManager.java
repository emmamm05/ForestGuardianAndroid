package org.forestguardian.Helpers;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.forestguardian.DataAccess.Local.NotificationItem;

import io.realm.Realm;

/**
 * Created by luisalonsomurillorojas on 25/7/17.
 */

public class NotificationsMessageManager extends FirebaseMessagingService {

    private static final String TAG = NotificationsMessageManager.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        // Save notification information
        NotificationItem item = new NotificationItem();
        item.setTitle(remoteMessage.getNotification().getTitle());
        item.setDescription(remoteMessage.getNotification().getBody());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();
    }

}
