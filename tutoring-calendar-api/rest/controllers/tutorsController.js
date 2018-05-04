// tutors controller routes
var express = require('express');
var router  = express.Router();
var mssql   = require('mssql');
var sql     = require('../sql');
var base64  = require('base64-img');

// get /api/tutors/
router.get('/', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor')

  for (i = 0; i < query.recordset.length; i++)
  {
    let img
    try {
      img = base64.base64Sync('assets/' + query.recordset[i]['ra'] + '.png')
    } catch (e) {
      img = base64.base64Sync('assets/no-image.png')
    }
  
    if (query.recordset[i]["description"] == null)
      query.recordset[i]["description"] = ""
  
    query.recordset[i]["image"] = img
  }

  res.json(query.recordset)
});

// get /api/tutors/:ra
router.get('/:ra', async (req,res) => {
  let img
  try {
    img = base64.base64Sync('assets/' + req.params.ra + '.png')
  } catch (e) {
    img = base64.base64Sync('assets/no-image.png')
  }

  let query = await sql.executeQuery('select * from tutor where ra = @ra', { name: 'ra', type: mssql.Int, value: req.params.ra })
  if (query.recordset[0]["description"] == null)
    query.recordset[0]["description"] = ""

  if (query.rowsAffected[0] > 0) {
    query.recordset[0]["image"] = img
    res.json(query.recordset[0])
  }
  else
    res.json({})
});

module.exports = router;
