var axios = require('axios')

module.exports = axios.create({
  baseURL: `http://localhost:8088`,
  crossdomain: true
})