package dev.turtlepaw.rnwear

import com.facebook.react.bridge.*

class RNWearModule internal constructor(context: ReactApplicationContext) :
  RNWearSpec(context) {

  private val manager = ConnectivityManager(context)

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  override fun getSendPath(promise: Promise) {
    return manager.getSendPath(promise)
  }

  @ReactMethod
  override fun send(payload: ReadableMap, promise: Promise) {
    return manager.sendData(payload, promise)
  }

  companion object {
    const val NAME = "NativeWear"
  }
}
