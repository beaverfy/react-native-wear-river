package dev.turtlepaw.rnwear

import com.facebook.react.bridge.*

abstract class RNWearSpec internal constructor(context: ReactApplicationContext) :
  ReactContextBaseJavaModule(context) {

  @ReactMethod
  abstract fun getSendPath(promise: Promise)

  @ReactMethod
  abstract fun send(payload: ReadableMap, promise: Promise)
}
