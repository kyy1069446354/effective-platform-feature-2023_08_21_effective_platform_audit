import { createStore } from 'vuex';
import app from './modules/app';
import user from './modules/user';
import tagsView from './modules/tagsView';
import permission from './modules/permission';
import settings from './modules/settings';
import getters from './getters';
import dict from './modules/dict'

const store = createStore({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings,
    dict,
  },
  getters,
});

export default store;
