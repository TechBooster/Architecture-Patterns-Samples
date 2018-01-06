import React from 'react';
import PropTypes from 'prop-types';
import {View, Text, StyleSheet, Button} from "react-native";

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    backgroundColor: 'white',
    marginTop: 10,
    marginBottom: 10,
  },
  textLayout: {
    padding: 10,
    backgroundColor: 'white',
  },
});

const DetailComponent = (props) => {
  return (
    <View style={styles.container}>
      <Text style={styles.textLayout}>
        {props.item}
      </Text>
      <Button
        onPress={() => props.onButtonPress(props.item)}
        title="Select"
      />
    </View>
  );
}

DetailComponent.propTypes = {
  item: PropTypes.string,
  onButtonPress: PropTypes.func,
}
export default DetailComponent;
