package io.apisitter.library.pushtest;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by francesco on 05/10/17.
 */

public class Firebase extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Log.i(TAG, "Vigolungo");
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        // sendRegistrationToServer(refreshedToken);
    }
}
