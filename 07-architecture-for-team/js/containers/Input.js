import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import InputComponent from '../components/InputComponent';
import { requestPosts } from '../actions';

//Container for input
class Input extends Component {
  constructor(props) {
    super(props);
    this.state = {
      hint: 'Input'
    };
  }
  render() {
    return (
      <InputComponent
        hint={this.state.hint}
        onButtonPress={(text) => this.startRequest(text)}
      />
    );
  }

  startRequest(text) {
    this.props.onButtonPress(text);
  }

}


const mapDispatchToProps = (dispatch) => {
  return {
    onButtonPress: bindActionCreators(requestPosts,dispatch),
  };
}

export default connect(null, mapDispatchToProps)(Input);
