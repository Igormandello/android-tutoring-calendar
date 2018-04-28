var mssql = require('mssql');

const config = {
  user: process.env.USER,
  password: process.env.PASS,
  server: process.env.HOST,
  database: process.env.DATABASE,
}

var con
(async function () {
    try {
        con = await mssql.connect(config)
    } catch (err) {
      console.log(err)
    }
})()

var sql = {
  executeQuery: async function(sql, /**/) {
    let args = Array.prototype.slice.call(arguments, 1);
  
    let request = con.request()
    for (i = 0; i < args.length; i++)
      request = request.input(args[i].name, args[i].type, args[i].value)
      
    let result = await request.query(sql)
    return result
  }
}

module.exports = sql