var axios = require('axios')

module.exports = axios.create({
  baseURL: `http://localhost:8088`,
  crossdomain: true,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
    'Access-Control-Allow-Origin': '*',
    'Origin': 'http://localhost:8088'
  }
})