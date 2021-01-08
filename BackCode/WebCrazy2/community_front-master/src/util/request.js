// 封装axios请求,便于后面的请求
import HttpRequest from './axios'
import config from '@/config'
const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.process

const axios = new HttpRequest(baseUrl)

export default axios
