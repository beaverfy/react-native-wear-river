import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';
import type { Payload } from './types';

export interface RNWearSpec extends TurboModule {
  send(data: Payload<string>): Promise<any>;
}

export default TurboModuleRegistry.getEnforcing<RNWearSpec>('RNWear');
