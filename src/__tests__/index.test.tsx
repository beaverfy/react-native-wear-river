import { green } from '../color';

it.todo('write a test');
it.only('test colors', () => {
  expect(green('')).toBe('\x1b[38;2;255;0;0m');
});
