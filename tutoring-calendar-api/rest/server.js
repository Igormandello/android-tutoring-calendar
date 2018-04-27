var express     = require('express');
var bodyParser  = require('body-parser');

var app         = express(); // Please do not remove this line, since CLI uses this line as guidance to import new controllers

require('dotenv').config()

var tutorSchedulesController = require('./controllers/tutorSchedulesController');
app.use('/api/tutorSchedules', tutorSchedulesController);


var tutorsController = require('./controllers/tutorsController');
app.use('/api/tutors', tutorsController);

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.listen(process.env.PORT || 3000, () => {
  console.log('Server is running');
});