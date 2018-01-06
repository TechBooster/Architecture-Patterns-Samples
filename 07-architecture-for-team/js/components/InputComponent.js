import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {View, StyleSheet, TextInput, Button, Platform } from 'react-native';

class InputComponent extends Component {

  constructor(props) {
    super(props);

    this.state = {
      text: props.hint
    }
  }
  render() {
    return (
      <View style={{
        flexDirection: 'row',
        justifyContent: 'space-between',
        backgroundColor: 'white',
        height: 48,
        paddingLeft: 10,
        paddingRight: (Platform.OS === 'android' ? 10 : 0),
      }}>
        <TextInput style={{
          height: 42,
          marginLeft: 10,
          marginRight: 10,
          flex: 3,
        }}
        underlineColorAndroid="blue"
        autoCapitalize={'none'}
        onChangeText={(text) => this.setState({text: text})}
        placeholder={this.state.text}/>
        <View style={{
          flex: 1,
          paddingTop:4,
          paddingBottom:4,
        }}>
        <Button
          onPress={() => this.props.onButtonPress(this.state.text)}
          title="Search"
          accessibilityLabel="alert"
        />
        </View>
      </View>
    );
  }
}

InputComponent.propTypes = {
  hint: PropTypes.string,
  onButtonPress: PropTypes.func.isRequired
}

export default InputComponent;
