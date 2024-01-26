import { NativeModules, Platform } from 'react-native';
import type { Payload } from './types';

const LINKING_ERROR =
  "The package 'react-native-wear' doesn't seem to be linked. Make sure: \n\n" +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go (see https://docs.expo.dev/develop/development-builds/create-a-build/)\n';

const PLATFORM_NOT_SUPPORTED_ERROR =
  'Platform not supported. This package only supports Android.';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const moduleProxy = (message: string) =>
  new Proxy(
    {},
    {
      get() {
        throw new Error(message);
      },
    }
  );

const WearModule = Platform.select({
  android: isTurboModuleEnabled
    ? require('./NativeWear').default
    : NativeModules.NativeWear,
  ios: moduleProxy(PLATFORM_NOT_SUPPORTED_ERROR),
  default: moduleProxy(PLATFORM_NOT_SUPPORTED_ERROR),
});

const RNWear = WearModule ? WearModule : moduleProxy(LINKING_ERROR);

export function send<T extends string>(payload: Payload<T>): Promise<any> {
  return RNWear.send(payload);
}
