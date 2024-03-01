import { PersistedStateOptions } from 'pinia-plugin-persistedstate'

/**
 * @description pinia持久化参数配置
 * @param {String} key 存储到持久化的 name
 * @param {Array} paths 需要持久化的 state name
 * @return persist
 * */

export const usePiniaPersist = (key: string, paths?: string[]) => {
  const piniaPersist: PersistedStateOptions = {
    key,
    storage: window.localStorage,
    // storage: window.sessionStorage,
    paths
  }
  return piniaPersist
}
