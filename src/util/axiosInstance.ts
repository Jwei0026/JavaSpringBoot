import axiosAPI from 'axios'

//使用create({config})方法创建axios实例
const axios = axiosAPI.create({
    baseURL:'http://localhost:8090', //请求后端数据的地址
    timeout: 2000                   //请求超时设置，单位ms
})

export default axios