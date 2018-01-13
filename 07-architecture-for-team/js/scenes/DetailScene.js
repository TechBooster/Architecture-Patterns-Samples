import React from 'react';
import { View } from 'react-native';
import Detail from '../containers/Detail';

const DetailScene = props => {
  return (
    <View>
      <Detail {...props}/>
    </View>
  );
}

export default DetailScene;
