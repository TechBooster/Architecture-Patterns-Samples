import {RECEIVE_POSTS} from '../actions';

const searchResult = (state = {}, action) => {
console.log(state);
console.log(action);
  switch (action.type) {
    case RECEIVE_POSTS:
      return {
        ...state,
        items: action.payload
      };

    default:
      return state;
  }
};
export default searchResult;
