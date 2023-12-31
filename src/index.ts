import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ReactNativeWear.web.ts
// and on native platforms to ReactNativeWear.ts
import ReactNativeWearModule from './ReactNativeWearModule';
import ReactNativeWearView from './ReactNativeWearView';
import { ChangeEventPayload, ReactNativeWearViewProps } from './ReactNativeWear.types';

// Get the native constant value.
export const PI = ReactNativeWearModule.PI;

export function hello(): string {
  return ReactNativeWearModule.hello();
}

export async function setValueAsync(value: string) {
  return await ReactNativeWearModule.setValueAsync(value);
}

const emitter = new EventEmitter(ReactNativeWearModule ?? NativeModulesProxy.ReactNativeWear);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ReactNativeWearView, ReactNativeWearViewProps, ChangeEventPayload };
