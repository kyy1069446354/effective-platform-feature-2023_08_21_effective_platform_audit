// import { login, logout, getLoginUserInfo } from '@/api/loginApi';
import * as loginApi from '@/api/loginApi';
import { getToken, setToken, removeToken } from '@/utils/token';
import defAva from '@/assets/images/profile.jpg';

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: '',
    permissions: [],
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_NAME: (state, name) => {
      state.name = name;
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar;
    },
    SET_ROLE: (state, roles) => {
      state.roles = roles;
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions;
    },
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim();
      const { password } = userInfo;
      const { code } = userInfo;
      const { uuid } = userInfo;
      return new Promise((resolve, reject) => {
        loginApi
          .login(username, password, code, uuid)
          .then((res) => {
            setToken(res.token);
            commit('SET_TOKEN', res.token);
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginApi
          .getLoginUserInfo()
          .then((res) => {
            // console.log(res);
            const avatar =
                res.avatar == '' || res.avatar == null ? defAva : import.meta.env.VITE_APP_BASE_API + res.avatar;
            // console.log("获取的roles"+ res.roles)
            // console.log(res.permissions)
            if (res.roles) {
              // 验证返回的roles是否是一个非空数组
              commit('SET_ROLE', res.roles);
              commit('SET_PERMISSIONS', res.permissions);
            } else {
              commit('SET_ROLE', 'ROLE_DEFAULT');
            }
            commit('SET_NAME', res.username);
            commit('SET_AVATAR', avatar);
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginApi
          .logout(state.token)
          .then(() => {
            commit('SET_TOKEN', '');
            commit('SET_ROLE', null);
            commit('SET_PERMISSIONS', []);
            removeToken();
            resolve();
          })
          .catch((error) => {
            commit('SET_TOKEN', '');
            commit('SET_ROLE', null);
            commit('SET_PERMISSIONS', []);
            removeToken();
            reject(error);
          });
      });
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise((resolve) => {
        commit('SET_TOKEN', '');
        removeToken();
        resolve();
      });
    },
  },
};

export default user;
