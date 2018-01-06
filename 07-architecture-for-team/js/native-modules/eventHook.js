import { NativeModules } from 'react-native';

export const nativeBack = () => {
  NativeModules.EventHook.sendEvent('nativeBack', {});
};

export const itemSelected = (item) => {
  NativeModules.EventHook.sendEvent('selected', { item });
}
