/**
 * ChuckNorrisViewer
 * https://github.com/tomoima525/ChuckNorrisViewer
 * @flow
 */

import React,{Component} from 'react';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import {createEpicMiddleware} from 'redux-observable';
import chuckNorris from './js/reducers';
import chuckEpic from './js/epics';
import App from './js/App';

import {AppRegistry} from 'react-native';

const epicMiddleware = createEpicMiddleware(chuckEpic);

const store = createStore(chuckNorris, applyMiddleware(epicMiddleware));

export default class ChuckNorrisViewer extends Component {

  render() {
    return (
      <Provider store={store}>
        <App/>
      </Provider>
    );
  }
}

AppRegistry.registerComponent('ChuckNorrisViewer', () => ChuckNorrisViewer);
