import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {ajax} from 'rxjs/observable/dom/ajax';
import {receivePosts} from '../actions';
import {REQUEST_POSTS} from '../actions';

export const getNorris = action$ => {
  return action$.ofType(REQUEST_POSTS).mergeMap(action => {
    return ajax.getJSON(`https://api.chucknorris.io/jokes/search?query=${action.payload}`).map(response => receivePosts(response));
  });
};
