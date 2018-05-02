const mongoose = require('mongoose');

const TutorSchema = mongoose.Schema({
  ra: Number,
  name: String,
  description: String,
  image: String
});

module.exports = mongoose.model('TutorModel', TutorSchema);