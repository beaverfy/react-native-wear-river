import * as React from 'react';

import { ReactNativeWearViewProps } from './ReactNativeWear.types';

export default function ReactNativeWearView(props: ReactNativeWearViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
