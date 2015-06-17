var mongoose = require('mongoose');

var zusageSchema = new mongoose.Schema({ 
	dabei: Boolean, 
	mitpartner: Boolean, 
	mitkind: Boolean,
	email: String,
	absage: Boolean 
});

module.exports = mongoose.model('zusage', zusageSchema, 'zusage');