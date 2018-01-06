import {REQUEST_POSTS} from '../actions';

const postByKey = (state = {}, action) => {
  switch (action.type) {
    case REQUEST_POSTS:
      return {...state,
          [action.payload]: state,
      };
    default:
      return state;
  }
};

export default postByKey;
