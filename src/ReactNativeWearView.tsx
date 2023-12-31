import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ReactNativeWearViewProps } from './ReactNativeWear.types';

const NativeView: React.ComponentType<ReactNativeWearViewProps> =
  requireNativeViewManager('ReactNativeWear');

export default function ReactNativeWearView(props: ReactNativeWearViewProps) {
  return <NativeView {...props} />;
}
