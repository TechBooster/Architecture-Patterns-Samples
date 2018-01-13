import React from 'react';
import { HeaderBackButton } from 'react-navigation';
import PropTypes from 'prop-types';
import { View, StyleSheet, TouchableHighlight, TouchableNativeFeedback, Text } from 'react-native';

// Component for Back Buttons

const styles = StyleSheet.create({
  barBackButtonText: {
    color: 'rgb(0, 122, 255)',
    textAlign: 'left',
    fontSize: 17,
    paddingLeft: 6,
  },
});

const BaseBackButton = props => (
  <HeaderBackButton
    onPress={() => props.onPress()}
    title={props.backTitle}
    titleStyle={styles.barBackButtonText}
  />
);

BaseBackButton.propTypes = {
  onPress: PropTypes.func.isRequired,
  backTitle: PropTypes.string.isRequired,
};

export const BackButton = props => (
  <BaseBackButton
    onPress={
      props.onPress ? props.onPress : Actions.pop
    }
    backTitle={props.backTitle}
  />
);
