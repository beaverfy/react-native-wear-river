import * as React from 'react';

import { Button, StyleSheet, View } from 'react-native';
import {
  send
} from 'react-native-wear';

export default function App() {
  const sendSampleData = () => {
    send("Hi there!");
  }

  return (
    <View style={styles.container}>
      <Button title="Send Sample Data" onPress={sendSampleData} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    rowGap: 16,
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
