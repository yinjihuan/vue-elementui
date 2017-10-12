import * as types from './mutation-types'

export default {

  showHeader:({commit}) => {
    commit(types.SHOW_HEADER);
  },
  hideHeader:({commit}) => {
    commit(types.HIDE_HEADER);
  }

}