export class RNWearError extends Error {
  constructor(message: string, method: string) {
    super(`ReactRNWear.${method}: ${message}`);
  }
}
