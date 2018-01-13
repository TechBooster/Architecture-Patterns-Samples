import {RECEIVE_POSTS, REQUEST_POSTS} from '../actions';

const visibilityFilter = (state = {}, action) => {
  switch (action.type) {
    case RECEIVE_POSTS:
      return {
        ...state,
        loading: false
      };
    case REQUEST_POSTS:
        return {
          ...state,
          loading: true
        };
    default:
      return state;
  }
};
export default visibilityFilter;
