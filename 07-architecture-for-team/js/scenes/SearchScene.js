import React from 'react';
import { View, StyleSheet } from 'react-native';
import Input from '../containers/Input';
import ResultList from '../containers/ResultList';

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
});

const SearchScene = () => {
  return (
    <View style={styles.container}>
      <Input/>
      <ResultList/>
    </View>
  );
}

export default SearchScene;
