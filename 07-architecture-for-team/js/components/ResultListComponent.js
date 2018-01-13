import React from 'react';
import PropTypes from 'prop-types';
import {ActivityIndicator, ListView, Text, StyleSheet, TouchableHighlight, View} from 'react-native';

const ResultListComponent = (props) => {
  const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
  console.log(props);

  if(props.isFetching) {
    return (
      <View>
        <ActivityIndicator style= {styles.centering}
        size="large"
      />
      </View>
  );
  }


  if(!props.items){
    return (
      <View>
        <Text style= {styles.rowLayout}>No result</Text>
      </View>
  );
  }

  if(props.items && !props.isFetching){
  return (
      <View>
        <ListView
          dataSource={ds.cloneWithRows(props.items)}
          renderRow={(data) => <Row obj={data} onPressRow={ (value) => props.onPressRow(value) }/>}
          renderSeparator={(sectionId, rowId) => <View key={rowId} style={styles.separator}/>}
          enableEmptySections={true}/>
      </View>
    );
  }
}

const Row = (props) => {
  return (
    <TouchableHighlight onPress={() => props.onPressRow(props.obj.value) } >
      <Text style= {styles.rowLayout}>{props.obj.value}</Text>
    </TouchableHighlight>
  );
}

ResultListComponent.propTypes = {
  items: PropTypes.array,
  isFetching: PropTypes.bool,
  onPressRow: PropTypes.func,
}

const styles = StyleSheet.create({
  centering: {
    alignItems: 'center',
    justifyContent: 'center',
    padding: 8,
  },
  rowLayout: {
    padding: 16,
    backgroundColor: '#FFFFFF'
  },
  separator: {
    flex: 1,
    height: StyleSheet.hairlineWidth,
    backgroundColor: '#8E8E8E'
  }
});

export default ResultListComponent;
