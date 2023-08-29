const dict = {
    state: () => ({
      dict: []
    }),
    mutations: {
      GET_DICT: (state, {key}) => {
        if (key == null && key === "") {
          return null;
        }
        try {
          for (let i = 0; i < state.dict.length; i++) {
            if (state.dict[i].key === key) {
              return state.dict[i].value;
            }
          }
        } catch (e) {
          return null;
        }
      },
      SET_DICT: (state, { key, value }) => {
        if (key !== null && key !== "") {
          state.dict.push({
            key: key,
            value: value
          });
        }
      },
      REMOVE_DICT: (state, {key}) => {
        try {
          for (let i = 0; i < state.dict.length; i++) {
            if (state.dict[i].key === key) {
              state.dict.splice(i, 1);
              return true;
            }
          }
        } catch (e) {
          return  false;
        }
      },
      // 清空字典
      CLEAN_DICT: (state) => {
        state.dict = []
      },
    },
    actions: {
      // 获取字典
      getDict({ commit }, data) {
        commit('GET_DICT', data);
      },
      // 设置字典
      setDict({ commit }, data) {
        commit('SET_DICT', data);
      },
      // 删除字典
      removeDict({ commit }, data) {
        commit('REMOVE_DICT', data);
      },
      // 清空字典
      cleanDict({ commit }, data) {
        commit('CLEAN_DICT', data);
      },
    }
  }

export default dict
