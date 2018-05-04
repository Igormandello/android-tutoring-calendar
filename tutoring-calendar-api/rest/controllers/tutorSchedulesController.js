// tutorSchedules controller routes
var express = require('express');
var router  = express.Router();
var mssql   = require('mssql');
var sql     = require('../sql');
var moment  = require('moment');

// get /api/tutorSchedules/
router.get('/', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor_schedule')
  res.json(query.recordset)
});

// get /api/tutorSchedules/:key
router.get('/:key', async (req,res) => {
  let key = req.params.key + ''
  if (key.length === 5) {
    let query = await sql.executeQuery('select * from tutor_schedule where tutor_ra = @ra', { name: 'ra', type: mssql.Int, value: key })

    for (i = 0; i < query.recordset.length; i++) {
      let d = moment(query.recordset[i].initial_hour).utcOffset(0)
      query.recordset[i].initial_hour = d.format('h:mm')
    }

    res.json(query.recordset)
  } else {
    let query = await sql.executeQuery('select * from tutor_schedule where weekday = @weekday', { name: 'weekday', type: mssql.Int, value: key})
    
    for (i = 0; i < query.recordset.length; i++) {
      let d = moment(query.recordset[i].initial_hour).utcOffset(0)
      query.recordset[i].initial_hour = d.format('H:mm')
    }

    res.json(query.recordset)
  }

  res.json('')
});

// get /api/tutorSchedules/:ra/:weekday
router.get('/:ra/:weekday', async (req,res) => {
  let query = await sql.executeQuery('select * from tutor_schedule where tutor_ra = @ra and weekday = @weekday', 
                                    { name: 'ra', type: mssql.Int, value: req.params.ra },
                                    { name: 'weekday', type: mssql.Int, value: req.params.weekday })

  for (i = 0; i < query.recordset.length; i++) {
    let d = moment(query.recordset[i].initial_hour).utcOffset(0)
    query.recordset[i].initial_hour = d.format('h:mm')
  }

  res.json(query.recordset)
});

module.exports = router;
