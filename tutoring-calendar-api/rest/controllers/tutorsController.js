// tutors controller routes
var express = require('express');
var router  = express.Router();
var mssql   = require('mssql');
var sql     = require('../sql')

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
router.get('/:ra/image', async (req,res) => {
  
});

module.exports = router;
