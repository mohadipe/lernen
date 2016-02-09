var zusageModel = require('./models/zusage_models.js');
var userModel = require('./models/user_models.js');

module.exports = function(app, passport) {
 
                // nur ein workaround solange die Hochzeitsseite benötigt wird.
                app.get('/', function(req, res) {
                                res.redirect('/index.html');
                }); 
                // show the home page (will also have our login links)
                app.get('/hochzeit/', function(req, res) {
                                res.render('home.ejs');
                });
 
                // PROFILE SECTION
                app.get('/hochzeit/profile', isLoggedIn, function(req, res) {
                                res.render('user_profile.ejs', {
                                                user : req.user
                                });
                });
 
                // LOGOUT
                app.get('/hochzeit/logout', function(req, res) {
                                req.logout();
                                res.redirect('/hochzeit/');
                });
 
//-----------------Authenticate login-----------
                // show the login form
                // TODO csrf token für mehr sicherheit: 1:01:37
                app.get('/hochzeit/login', function(req, res) {
                                res.render('user_login.ejs', { message: req.flash('loginMessage') });
                });

                // process the login form
                app.post('/hochzeit/login', passport.authenticate('local-login', {
                                successRedirect : '/hochzeit/zusage', // redirect to the secure zusage section
                                failureRedirect : '/hochzeit/login', // redirect back to the signup page if there is an error
                                failureFlash : true // allow flash messages
                }));
 
//----------------User Regitration------
                // show the signup form
                app.get('/hochzeit/register', function(req, res) {
                                res.render('user_registration.ejs', { message: req.flash('loginMessage') });
                });

                // process the signup form
                app.post('/hochzeit/register', passport.authenticate('local-signup', {
                                successRedirect : '/hochzeit/profile', // redirect to the secure zusage section
                                failureRedirect : '/hochzeit/register', // redirect back to the signup page if there is an error
                                failureFlash : true // allow flash messages
                }));
 
//---------------Authorizing already logining
                // local login
                app.get('/hochzeit/connect/local', function(req, res) {
                                res.render('user_login.ejs', { message: req.flash('loginMessage') });
                });
                app.post('/hochzeit/connect/local', passport.authenticate('local-signup', {
                                successRedirect : '/hochzeit/zusage', // redirect to the secure zusage section
                                failureRedirect : '/hochzeit/local', // redirect back to the signup page if there is an error
                                failureFlash : true // allow flash messages
                }));
 
//---------------Unlink Account
/* used to unlink accounts. for social accounts, just remove the token
 for local account, remove email and password
 user account will stay active in case they want to reconnect in the future
*/
                // local login
                app.get('/hochzeit/unlink/local', function(req, res) {
                                var user            = req.user;
                                user.local.email    = undefined;
                                user.local.password = undefined;
                                user.nachname       = undefined;
                                user.vorname        = undefined;
                                user.save(function(err) {
                                                res.redirect('/hochzeit/profile');
                                });
                });

                app.get('/hochzeit/zusage', isLoggedIn,
                        function(req, res) {
                                 zusageModel.findOne({'email': req.user.local.email }, 
                                        function(err, gefunden) {
                                                if (err) return handleError(err);
                                                if (gefunden) {
                                                        console.log('etwas gefunden:' + gefunden);
                                                        res.render('zusage.ejs', {'item': gefunden, 'message':""});
                                                } else {
                                                        console.log('nichts gefunden.');
                                                        var tmp = {dabei: false, mitPartner: false, mitKind: false, absage: false}; 
                                                        res.render('zusage.ejs', {'item': tmp, 'message':""});
                                                }
                                        }
                                )
                        }
                );

                app.post('/hochzeit/change/zusage', isLoggedIn,
                        function(req, res) {
                                // console.log('/change/zusage | req user : ' + req.user);
                                var email = req.user.local.email;
                                var query = {'email': email};
                                var update = {'dabei': req.body.dabei, 'mitpartner': req.body.mitpartner, 'mitkind': req.body.mitkind, 'absage': req.body.absage, 'email': email};
                                var options = {upsert: true};
                                // find and update or create new
                                zusageModel.findOneAndUpdate(query, update, options, function(err, aktuellesObj) {
                                                if (err) return handleError(err);
                                                // console.log('Aktuallisierte Zusage: ' + aktuellesObj);
                                                res.render('zusage.ejs', {'item': aktuellesObj, 'message' : "Erfolgreich"});
                                        }
                                );
                                //res.redirect('/hochzeit/zusage');
                        }
                );

                app.get('/hochzeit/gaeste', isLoggedIn,
                        function(req, res) {
                            // alle registrierten benutzer
                            userModel.find({}, function(err, allUser) {
                                    if (err) return handleError(err);
                                    if (!allUser) {
                                        // In diesem Fall gibt es keine User
                                        var keineGaeste = 'Keine Gäste eingetragen.';
                                        res.render('gaeste.ejs', {'gaeste': null, 'error': keineGaeste});
                                    }
                                    zusageModel.find({}, function(err, allZusagen) {
                                            if (err) return handleError(err);
                                            if (!allZusagen) {
                                                // In diesem Fall gibt es keine User
                                                var keineZusagen = 'Keine Zusagen eingetragen.';
                                                res.render('gaeste.ejs', {'gaeste': null, 'error': keineZusagen});
                                            }
                                            var gaeste = new Array()
                                            for (var i = allUser.length - 1; i >= 0; i--) {
                                                for (var j = allZusagen.length - 1; j >= 0; j--) {
                                                    if (allZusagen[j].email == allUser[i].local.email) {
                                                        // console.log("Name + Vorname: " + allUser[i].vorname + " " + allUser[i].nachname);
                                                        gaeste.push({ 
                                                            'name': allUser[i].vorname + ' ' + allUser[i].nachname,
                                                            'dabei': allZusagen[j].dabei ? 'Ja' : 'Nein', 
                                                            'mitpartner': allZusagen[j].mitpartner ? 'Ja' : 'Nein', 
                                                            'mitkind': allZusagen[j].mitkind ? 'Ja' : 'Nein',
                                                            'absage': allZusagen[j].absage ? 'Ja' : 'Nein'});
                                                    }
                                                };
                                            };
                                            res.render('gaeste.ejs', {'gaeste': gaeste, 'error': null});
                                        }
                                    );
                                }
                            );
                        }
                );

                app.get('/hochzeit/bilder', isLoggedIn,
                        function(req, res) {res.render('bilder.ejs');});
 
                app.get('/hochzeit/info', isLoggedIn,
                        function(req, res) {res.render('infos.ejs');});
                app.get('/hochzeit/ansprechpartner', isLoggedIn,
                        function(req, res) {res.render('trauzeugen.ejs');});                
                app.post('/hochzeit/change/profile', isLoggedIn, function(req, res) {
                        console.log('bin in /change/profile');
                        var email = req.user.local.email;
                        var query = {'local.email': email};
                        var update = {$set:{
                                'nachname': req.body.nachname,
                                'vorname': req.body.vorname,
                                'local' : {
                                    'email' : req.user.local.email,
                                    'password' : req.user.local.password
                                    }
                                }};
                        var options = {upsert: true};

                        userModel.findOneAndUpdate(query, update, options, function(err, aktuellesObj) {
                                                if (err) return handleError(err);
                                                // console.log('Aktuallisierte Zusage: ' + aktuellesObj);
                                                res.render('user_profile.ejs', {user : aktuellesObj});
                                        }
                                );
                });
};
 
// route middleware to ensure user is logged in
function isLoggedIn(req, res, next) {
                if (req.isAuthenticated())
                                return next();               
                res.redirect('/hochzeit/login');
}