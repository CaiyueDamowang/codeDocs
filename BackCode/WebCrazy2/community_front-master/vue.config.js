// 如果前后端应用API服务器没有运行在同一个主机，将API请求代理到API服务器
module.exports = {
  devServer: {
    proxy: 'http://localhost:3000'
  }
}
