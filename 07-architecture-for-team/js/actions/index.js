/*
 * Action types
 */

export const RECEIVE_POSTS = 'RECEIVE_POSTS';
export const REQUEST_POSTS = 'REQUEST_POSTS';

/*
 * Action creators
 */

export const requestPosts = ( key ) => {
  return {
    type: REQUEST_POSTS,
    payload: key,
    isFetching: true,
  }
};

export const receivePosts = ( response ) => {
  return {
    type: RECEIVE_POSTS,
    payload: response.result,
    isFetching: false,
  }
};
