import axios from 'axios'

const Axios = axios.create({
  timeout: 10000
})

//POST传参序列化(添加请求拦截器)
Axios.interceptors.request.use(config => {
    // 在发送请求之前做某件事
    // if(config.method  === 'post'){
    //     // JSON 转换为 FormData
    //     const formData = new FormData()
    //     Object.keys(config.data).forEach(key => formData.append(key, config.data[key]))
    //     config.data = formData
    // }

    // 下面会说在什么时候存储 token
    if (sessionStorage.token) {   
        config.headers.Authorization = sessionStorage.token
    }
    return config
},error =>{
    return Promise.reject(error)
})

//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(res =>{
    //需要登陆
    if(res.data.code == 401 || res.data.code == 402 || res.data.code == 403) {
      window.location = "/login";
    }
    return res
}, error => {
    return Promise.reject(error)
})

Axios.install = (Vue) => {
  Vue.prototype.$http = Axios
}

export default Axios
