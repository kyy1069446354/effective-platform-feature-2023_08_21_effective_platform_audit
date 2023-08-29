import store from '../store';
import { getDictType } from '@/api/system/configApi';
/**
 * 获取字典数据
 */
export function useDict(...args) {
  const res = ref({});
  return (() => {
    args.forEach((d, index) => {
      res.value[d] = [];
      const dictDataList = store.commit('GET_DICT', d);
      if (dictDataList) {
        res.value[d] = dictDataList.map((p) => ({ label: p.label, value: p.value, elTagType: p.listClass, elTagClass: p.cssClass }));
      } else {
        getDictType(d).then((resp) => {
          res.value[d] = resp.map((p) => ({ label: p.label, value: p.value, elTagType: p.listClass, elTagClass: p.cssClass }));
          store.commit('SET_DICT', {key: d, value: res.value[d]});
        });
      }
    });
    return toRefs(res.value);
  })();
}

// 回显数据字典
export function selectDictLabel(dicts, value) {
  if (value === undefined) {
    return '';
  }
  const actions = [];
  Object.keys(dicts).some((key) => {
    if (dicts[key].value === `${value}`) {
      actions.push(dicts[key].label);
      return true;
    }
  });
  if (actions.length === 0) {
    actions.push(value);
  }
  return actions.join('');
}

// 回显数据字典（字符串数组）
export function selectDictLabels(datas, value, separator) {
  if (value === undefined) {
    return '';
  }
  const actions = [];
  const currentSeparator = undefined === separator ? ',' : separator;
  const temp = value.split(currentSeparator);
  Object.keys(value.split(currentSeparator)).some((val) => {
    let match = false;
    Object.keys(datas).some((key) => {
      if (datas[key].value === `${temp[val]}`) {
        actions.push(datas[key].label + currentSeparator);
        match = true;
      }
    });
    if (!match) {
      actions.push(temp[val] + currentSeparator);
    }
  });
  return actions.join('').substring(0, actions.join('').length - 1);
}
