const mongoose = require('mongoose');

const TutorSchema = mongoose.Schema({
  ra: Number,
  name: String,
  description: String
});

module.exports = mongoose.model('TutorModel', TutorSchema);