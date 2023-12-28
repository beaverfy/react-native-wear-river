import { DeviceEventEmitter, NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-respondr_react_native_wear_module' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n';

const ReactNativeWearCommunicationModule =
  NativeModules.ReactNativeWearCommunicationModule
    ? NativeModules.ReactNativeWearCommunicationModule
    : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

// Listen for the log event
DeviceEventEmitter.addListener('logEvent', (message) => {
  console.log("[ReactNativeWear] ", message);
});

export function sendDataToClient(data: Object, options: {
  debugLogs: boolean;
}): void {
  if (options.debugLogs) console.log("[ReactNativeWear] Sending data to wear client...")
  return ReactNativeWearCommunicationModule.sendDataToClient(data, options.debugLogs);
}

export function getRNEventName(): string {
  return ReactNativeWearCommunicationModule.getEventName();
}
