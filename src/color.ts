export function red(text: string) {
  return `\x1b[38;2;255;0;0m` + text + `\x1b[0m`;
}

export function green(text: string) {
  return `\x1b[38;2;0;155;0m` + text + `\x1b[0m`;
}

export function yellow(text: string) {
  return `\x1b[38;2;255;247;0m` + text + `\x1b[0m`;
}

export function blue(text: string) {
  return `\x1b[38;2;0;85;255m` + text + `\x1b[0m`;
}

export function purple(text: string) {
  return `\x1b[38;2;128;0;128m` + text + `\x1b[0m`;
}
