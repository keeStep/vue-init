// 解决开发环境跨域问题
// Node.js 请求转发的代理
let proxyObj = {};
proxyObj['/'] = { // 拦截所有请求进行转发
    ws: false, // 关闭 websocket
    target: 'http://localhost:8081', // 转发目标地址
    changeOrigin: true, // 转发的必要设置？
    pathRewrite: { // 请求地址是否修改，我们不做修改
        '^/': ''
    }
}

// 转发规则 设置 【比如 localhost 用代理 ProxyObj 来进行转发】
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}