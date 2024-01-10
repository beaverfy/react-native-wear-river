package expo.modules.wear

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;

class ReactNativeWearModule : Module() {
  private val RECIEVE_EVENT_NAME = "dataRecieved";
  private val TAG = "ReactNativeWearModule";
  private val dataClient by lazy { Wearable.getDataClient(this) }
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ReactNativeWear')` in JavaScript.
    Name("ReactNativeWear")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants(
      "DataReceivedEvent" to RECIEVE_EVENT_NAME
    )

    // Defines event names that the module can send to JavaScript.
    Events(RECIEVE_EVENT_NAME)

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("sendDataAsync") { value: Record, path: String ->
      Log.d(TAG, "Attempting to send data...")
      val putDataMapReq = PutDataMapRequest.create(path);
      ReadableMapKeySetIterator iterator = data.keySetIterator();
      while (iterator.hasNextKey()) {
        val key = iterator.nextKey();
        ReadableType readableType = data.getType(key);
        switch (readableType) {
          case String:
            String stringValue = data.getString(key);
            putDataMapReq.getDataMap().putString(key, stringValue);
            break;
          case Number:
            double numberValue = data.getDouble(key);
            putDataMapReq.getDataMap().putDouble(key, numberValue);
            break;
          case Boolean:
            boolean booleanValue = data.getBoolean(key);
            putDataMapReq.getDataMap().putBoolean(key, booleanValue);
            break;
        }
      }

      Task<Integer> putDataTask = dataClient.putDataItem(putDataMapReq.asPutDataRequest());
      putDataTask.addOnSuccessListener({ ->
          Log.d(TAG, "Data sent successfully");
      });

      putDataTask.addOnFailureListener({ ->
        Log.d(TAG, "Failed to send data", e);
      });
    }
  }
}
