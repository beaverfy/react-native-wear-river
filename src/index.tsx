import { DeviceEventEmitter, NativeModules, Platform } from 'react-native';
import { green } from './color';
import { useState } from 'react';

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
      )} Debug logs enabled, learn more: https://reactnativewear.vercel.app/guides/debugging`
    );

  return ReactNativeWearCommunicationModule.sendDataToClient(
    data
  );
}

export function useWearData<T>(dataResolver: (data: any) => T | Promise<T>) {
  const [data, setData] = useState<T>();
  DeviceEventEmitter.addListener(getRNEventName(), async (data) => {
    const resolved = await dataResolver(data);
    return setData(resolved);
  });

  return {
    data
  }
}

export function getRNEventName(): string {
  return ReactNativeWearCommunicationModule.getEventName();
}
