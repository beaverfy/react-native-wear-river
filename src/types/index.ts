export interface Payload<T extends string> {
  [key: string]: T;
}
