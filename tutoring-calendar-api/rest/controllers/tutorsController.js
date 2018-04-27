// tutors controller routes
var express = require('express');
var router  = express.Router();
var mssql   = require('mssql');
var sql     = require('../sql')
var base64  = require('base64-img');

// get /api/tutors/
router.get('/', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor')
  res.json(query.recordset)
});

// get /api/tutors/:ra
router.get('/:ra', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor where ra = @ra', { name: 'ra', type: mssql.Int, value: req.params.ra })
  
  if (query.rowsAffected[0] > 0)
    res.json(query.recordset[0])
  else
    res.json({})
});

// get /api/tutors/:ra/image
router.get('/:ra/image', (req,res) => {
  let result
  try {
    result = base64.base64Sync('assets/' + req.params.ra + '.png')
  } catch (e) {
    result = base64.base64Sync('assets/no-image.png')
  }
  
  res.json(result)
});

module.exports = router;
