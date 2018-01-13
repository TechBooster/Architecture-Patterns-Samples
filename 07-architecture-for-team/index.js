/**
 * ChuckNorrisViewer
 * https://github.com/tomoima525/ChuckNorrisViewer
 * @flow
 */

import React,{Component} from 'react';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import PropTypes from 'prop-types';
import {createEpicMiddleware} from 'redux-observable';
import { composeWithDevTools } from 'remote-redux-devtools';
import chuckNorris from './js/reducers';
import chuckEpic from './js/epics';
import App from './js/App';

import {AppRegistry} from 'react-native';

const composeEnhancers = composeWithDevTools({ realtime: true, port: 8000 });
const epicMiddleware = createEpicMiddleware(chuckEpic);

const store = createStore(chuckNorris, composeEnhancers(applyMiddleware(epicMiddleware)));

export default class ChuckNorrisViewer extends Component {

  // Called from App. Child View Component can get values from here.
  getChildContext() {
    const { initialScene, data } = this.props;
    if (data && typeof (data) === 'object') {
      return { data, initialScene };
    }
    return { initialScene }; // No data
}
  render() {
    return (
      <Provider store={store}>
        <App/>
      </Provider>
    );
  }
}

ChuckNorrisViewer.childContextTypes = {
  data: PropTypes.oneOfType([
    PropTypes.shape({ }),
    PropTypes.string,
  ]),
  initialScene: PropTypes.string,
};

AppRegistry.registerComponent('ChuckNorrisViewer', () => ChuckNorrisViewer);
