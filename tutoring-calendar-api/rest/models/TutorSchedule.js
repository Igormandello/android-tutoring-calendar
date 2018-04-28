const mongoose = require('mongoose');

const TutorScheduleSchema = mongoose.Schema({
  id: Number,
  tutorRA: Number,
  initialHour: Date,
  duration: Number,
  weekday: Number,
  place: String
});

module.exports = mongoose.model('TutorScheduleModel', TutorScheduleSchema);