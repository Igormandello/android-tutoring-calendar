// tutors controller routes
var express = require('express');
var router  = express.Router();
var mssql   = require('mssql');
var sql     = require('../sql');
var base64  = require('base64-img');

// get /api/tutors/
router.get('/', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor')
  res.json(query.recordset)
});

// get /api/tutors/:ra
router.get('/:ra', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor where ra = @ra', { name: 'ra', type: mssql.Int, value: req.params.ra })
  
  if (query.rowsAffected[0] > 0) {
    let img
    try {
      img = base64.base64Sync('assets/' + req.params.ra + '.png')
    } catch (e) {
      img = base64.base64Sync('assets/no-image.png')
    }

    query.recordset[0]["image"] = img
    res.json({})//query.recordset[0])
  }
  else
    res.json({})
});

module.exports = router;
