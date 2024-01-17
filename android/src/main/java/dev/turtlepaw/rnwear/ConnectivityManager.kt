package dev.turtlepaw.rnwear

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.react.bridge.ActivityEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.google.android.gms.wearable.PutDataMapRequest
import java.time.Instant
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.wearable.Wearable
import kotlin.coroutines.cancellation.CancellationException

class ConnectivityManager(private val applicationContext: ReactApplicationContext) :
ActivityEventListener {
  private val dataClient by lazy { Wearable.getDataClient(applicationContext) }

  fun sendData(payload: ReadableMap, promise: Promise) {
        try {
          val request = PutDataMapRequest.create(SEND_PATH).apply {
            dataMap // place payload into data map
          }
            .asPutDataRequest()
            .setUrgent()

          val result = dataClient.putDataItem(request);

          Log.d(TAG, "DataItem saved: $result")
          return promise.resolve(true)
        } catch (cancellationException: CancellationException) {
          throw cancellationException
        } catch (exception: Exception) {
          Log.d(TAG, "Saving DataItem failed: $exception")
          return promise.resolve(false)
        }
  }

  companion object {
    const val TAG = "RNWear"
    const val SEND_PATH = "/rnwear-send"
  }

  init {
    applicationContext.addActivityEventListener(this)
  }
}

