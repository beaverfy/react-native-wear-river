export class RNWearError extends Error {
  constructor(message: string, method: string) {
    super(`ReactNativeWear.${method}: ${message}`);
  }
}
