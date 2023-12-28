import { DeviceEventEmitter, NativeModules, Platform } from 'react-native';
import { green } from './color';

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

function getRNLogEventName(): string {
  return ReactNativeWearCommunicationModule.getLogEventName();
}

// Listen for the log event
DeviceEventEmitter.addListener(getRNLogEventName(), (message) => {
  console.log('[ReactNativeWear] ', message);
});

export function sendDataToClient(
  data: Object,
  {
    debugLogs = false,
  }: {
    debugLogs: boolean;
  }
): void {
  if (debugLogs)
    console.log(
      `[ReactNativeWear] ${green(
        '✔'
      )} Debug logs enabled, learn more: https://reactnativewear.vercel.app/`
    );

  return ReactNativeWearCommunicationModule.sendDataToClient(
    data,
    debugLogs ?? false
  );
}

export function getRNEventName(): string {
  return ReactNativeWearCommunicationModule.getEventName();
}
