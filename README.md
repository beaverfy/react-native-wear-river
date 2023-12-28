# [React Native Wear][1]
#### This is a fork of [circles-00/react-native-wear-communication-module](https://github.com/circles-00/react-native-wear-communication-module) compatible with expo-manged projects

React Native Wear is a expo native module for expo-manged and bare react native applications that allows communicating with WearOS devices through native data client API. More information <a href="https://developers.google.com/android/reference/com/google/android/gms/wearable/DataClient">here</a>. <br/>
<br />
This module is helpful if you're developing an application for android and Wear OS

### [💡 We're building new docs][1]
> We're building new docs for this library that are more detailed and more up-to-date
## Before you start
### Make sure that both of your applications:
- Share the same package name everywhere in the project (android manifest, `build.gradle`, the project files etc.)
- Share the same `applicationId` in `build.gradle`
- Are signed with the same key (for development and debugging both of the applications can be unsigned, triple check this, as you can lose a lot of time if not done right)

### Triple check the above steps
## Installation
### Expo
```sh
npx expo install react-native-wear
```
### Bare
Using your package manager of choice:
```sh
yarn add react-native-wear
```

## Usage
### Sending data to a Client
This example gets an access token and it's expiry time and sends it to the Wear OS app:
```js
import { sendDataToClient } from "react-native-wear-communication-module";

fetch("URL", {
  method: "POST"
}).then(({data}) => {
  const { accessToken, validTo } = data;
  sendDataToClient({
    accessToken,
    validTo
  });
}).catch((err) => console.error(err))

```

### Receiving data from a Client
This example logs any recieved data from the Wear OS app:
```js
import { getRNEventName } from "react-native-wear-communication-module";
import { DeviceEventEmitter } from 'react-native';

const onReceiveEventHandler = (...data) => {
  console.log(...data)
  // Some logic
  // You would probably want to use the sendDataToClient function here, but not necessarily,
  // You can use the listener for any logic that you need in the application
}

useEffect(() => {
  DeviceEventEmitter.addListener(getRNEventName(), onReceiveEventHandler);
}, [])

```

### Receiving and Sending data on the WearOS Application
For receiving data on the WearOS application please refer <a href="https://developer.android.com/training/wearables/data/events">here</a>

## Note: First read the above link, then -

- To successfully send data to the React-Native application make a request to: "/data-query" using the native Data-Client
- For receiving data on the WearOS application, you need to listen on this path: "/data-response"

#### Example coming soon...
## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

[1]: https://reactnativewear.vercel.app/