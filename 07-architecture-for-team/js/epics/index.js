import { combineEpics } from 'redux-observable';
import { getNorris } from './posts';

const chuckEpic = combineEpics(getNorris);

export default chuckEpic;
