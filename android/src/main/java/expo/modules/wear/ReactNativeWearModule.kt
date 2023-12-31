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
  private static final RECIEVE_EVENT_NAME = "dataRecieved";
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
      "PI" to Math.PI
    )

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      "Hello world! 👋"
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { value: String ->
      // Send an event to JavaScript.
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ReactNativeWearView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: ReactNativeWearView, prop: String ->
        println(prop)
      }
    }
  }

  @ReactMethod
  public void sendData(WritableMap data) {
    PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/data");
    ReadableMapKeySetIterator iterator = data.keySetIterator();
    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
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

    Task<Integer> putDataTask = Wearable.getDataClient(getReactApplicationContext()).putDataItem(putDataMapReq.asPutDataRequest());
    putDataTask.addOnSuccessListener(new OnSuccessListener<Integer>() {
      @Override
      public void onSuccess(Integer integer) {
        Log.d(TAG, "Data sent successfully");
      }
    });
    putDataTask.addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Failed to send data", e);
      }
    });
  }

  public void sendEvent(String data) {
    WritableMap params = Arguments.createMap();
    params.putString("data", data);
    getReactApplicationContext()
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(EVENT_NAME, params);
  }
}
