package dev.turtlepaw.rnwear

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import kotlin.coroutines.cancellation.CancellationException


class ConnectivityManager(private val applicationContext: ReactApplicationContext) {
  private val dataClient by lazy { Wearable.getDataClient(applicationContext) }

  fun sendData(payload: ReadableMap, promise: Promise) {
        try {
          val request = PutDataMapRequest.create(SEND_PATH).apply {
            val iterator = payload.keySetIterator()
            while(iterator.hasNextKey()){
              val key = iterator.nextKey()
              val readableType: ReadableType = payload.getType(key)
              when (readableType) {
                ReadableType.String -> {
                  val stringValue = payload.getString(key)
                  dataMap.putString(key, stringValue)
                }

                ReadableType.Number -> {
                  val numberValue = payload.getDouble(key)
                  dataMap.putDouble(key, numberValue)
                }

                ReadableType.Boolean -> {
                  val booleanValue = payload.getBoolean(key)
                  dataMap.putBoolean(key, booleanValue)
                }

                else -> {
                  // @TODO: Handle other data values
                }
              }
            }
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
}

