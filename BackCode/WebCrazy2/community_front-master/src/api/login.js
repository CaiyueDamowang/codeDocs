import axios from '@/util/request'

/**
 * 获取验证码接口
 * @param {*} sid 唯一标识
 */
const getCode = async (sid) => {
  return axios.get('WebCrazy2/web/crazy/kapt', {
    params: {
      sid
    }
  })
}

/**
 * 找回密码接口
 * @param {*} option 用户信息(邮箱，验证码)
 */

const forget = async (option) => {
  let result = ''
  try {
    result = await axios.post('/login/forget', {
      ...option
    })
    if (result.status === 200) {
      return result.data
    }
  } catch (e) {
    // console.log(e)
  }
  return result
}

/**
 * 用户登录接口
 * @param {*} loginInfo 用户登录信息
 */
const login = (loginInfo) => {
  return axios.post('/login/login', {
    ...loginInfo
  })
}
/**
 * 用户注册接口
 * @param {*} regInfo 用户注册信息
 */
const reg = (regInfo) => {
  return axios.post('/login/reg', {
    ...regInfo
  })
}
export { getCode, forget, login, reg }
