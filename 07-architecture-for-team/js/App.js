import React, { Component } from 'react';
import { View } from 'react-native';
import Scenes from './scenes';

class App extends Component {
  render() {
    return (
       <View style={{ flex: 1 }}>
         <Scenes />
       </View>
    );
  }
}

export default App;
