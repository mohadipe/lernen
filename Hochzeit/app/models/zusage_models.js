var mongoose = require('mongoose');

var zusageSchema = new mongoose.Schema({ 
	dabei: Boolean, 
	mitpartner: Boolean, 
	mitkind: Boolean,
	email: String 
});

module.exports = mongoose.model('zusage', zusageSchema, 'zusage');