package io.apisitter.library.pushtest;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class PushHandlerService extends FirebaseMessagingService {
    public PushHandlerService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Log.d(TAG, "Arrivato messaggio push");
        Log.d(TAG, "" + remoteMessage.getMessageType());
        Log.d(TAG, "" + remoteMessage.getCollapseKey());
        Log.d(TAG, "" + remoteMessage.getFrom());
        Log.d(TAG, "" + remoteMessage.getMessageId());
        Log.d(TAG, "" + remoteMessage.getTo());
        Map data = remoteMessage.getData();
        if(data.isEmpty()){
            Log.d(TAG, "data è vuoot");
        }
        for ( Object key : data.keySet() ) {
            Log.d(TAG, "" + key.toString());
            Log.d(TAG, "" + key);
        }
        Log.d(TAG, "" + remoteMessage.getData());
        Log.d(TAG, "" + remoteMessage.getSentTime());
        Log.d(TAG, "" + remoteMessage.getTtl());
        Log.d(TAG, "" + remoteMessage.getNotification());
//        Log.d(TAG, "" + remoteMessage.());


    }
}
