import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { View } from 'react-native';
import { connect } from 'react-redux';
import DetailComponent from '../components/DetailComponent';
import { itemSelected } from '../native-modules/eventHook';

//Container for detail
const Detail = (props) => {
    return (
      <View>
        <DetailComponent
          item={props.item}
          onButtonPress={ itemSelected }
        />
      </View>
    );
}

Detail.propTypes = {
  item: PropTypes.string,
}

export default connect()(Detail);
