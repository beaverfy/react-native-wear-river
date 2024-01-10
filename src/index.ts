import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ReactNativeWear.web.ts
// and on native platforms to ReactNativeWear.ts
import ReactNativeWearModule from './ReactNativeWearModule';
import ReactNativeWearView from './ReactNativeWearView';
import { DataReceivedEventPayload, ReactNativeWearViewProps, SendPayload } from './ReactNativeWear.types';

// Get the native constant value.
export const DataReceivedEvent = ReactNativeWearModule.DataReceivedEvent;

export async function send(value: SendPayload) {
  return await ReactNativeWearModule.sendDataAsync(value);
}

const emitter = new EventEmitter(ReactNativeWearModule ?? NativeModulesProxy.ReactNativeWear);

export function addDataListener(listener: (event: DataReceivedEventPayload) => void): Subscription {
  return emitter.addListener<DataReceivedEventPayload>('onChange', listener);
}

export { ReactNativeWearView, ReactNativeWearViewProps, DataReceivedEventPayload };
