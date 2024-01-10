import { StyleSheet, Text, View } from 'react-native';

import * as ReactNativeWear from 'react-native-wear';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Send Data</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
