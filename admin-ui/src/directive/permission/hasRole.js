/**
 * v-hasRole 角色权限处理
 * Copyright (c) 2019 ruoyi
 */

import store from '@/store';

export default {
  mounted(el, binding, vnode) {
    const { value } = binding;
    const superAdmin = 'admin';
    const currentRoles = store.getters && store.getters.roles;

    if (value && value instanceof Array && value.length > 0) {
      const roleFlag = value;

      const hasRole = currentRoles.some(role => {
        return superAdmin === role || roleFlag.includes(role)
      })

      if (!hasRole) {
        el.parentNode && el.parentNode.removeChild(el);
      }
    } else {
      throw new Error('请设置角色权限标签值"');
    }
  },
};
