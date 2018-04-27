var express     = require('express');
var bodyParser  = require('body-parser');

var app         = express(); // Please do not remove this line, since CLI uses this line as guidance to import new controllers


var tutorschedulesController = require('./rest/controllers/tutorschedulesController');
app.use('/api/tutorschedules', tutorschedulesController);


var tutorsController = require('./rest/controllers/tutorsController');
app.use('/api/tutors', tutorsController);

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.listen(process.env.PORT || 3000, () => {
  console.log('Server is running');
});