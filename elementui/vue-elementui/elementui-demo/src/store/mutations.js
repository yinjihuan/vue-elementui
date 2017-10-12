import * as types from './mutation-types'
import getters from './getters'

const state = {
  headerStatus:true
};

const mutations = {
  [types.SHOW_HEADER](state) {
    state.headerStatus = true;
  },
  [types.HIDE_HEADER](state) {
    state.headerStatus = false;
  }
};

export default {
  state,
  mutations,
  getters
}