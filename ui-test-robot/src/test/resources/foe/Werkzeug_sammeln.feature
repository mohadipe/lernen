Feature: Sammeln der Werkzeugproduktion

Scenario: Von einer Schmiede Werkzeug sammeln.
	Given Die Werkzeugproduktion einer Schmiede ist fertig.
	When Nach fertigen Werkzeugproduktionen gesucht wird.
	Then Die fertige Werkzeugproduktion gefunden.
	
#Scenario: Von einer Schmiede mit Stern Werkzeuge sammeln.
#	Given Die Werkzeugproduktion einer Schmiede mit Stern ist fertig.
#	When Nach fertigen Werkzeugproduktionen gesucht wird.
#	Then Die fertige Werkzeugproduktion gefunden.