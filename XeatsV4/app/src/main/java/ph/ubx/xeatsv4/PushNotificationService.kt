package ph.ubx.xeatsv4

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        Log.d("TAG", "The token refresh: $p0")
    }
}