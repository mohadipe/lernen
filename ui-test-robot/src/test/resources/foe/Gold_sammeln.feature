Feature: Sammeln der Münzproduktion

Scenario: Von einem Wohnhaus Münzen sammeln.
	Given Die Münzproduktion eines Wohnhauses ist fertig.
	When Nach fertigen Münzproduktionen gesucht wird.
	Then Die fertige Münzproduktion gefunden.
	
Scenario: Von einem Wohnhaus mit Stern Münzen sammeln.
	Given Die Münzproduktion eines Wohnhauses mit Stern ist fertig.
	When Nach fertigen Münzproduktionen gesucht wird.
	Then Die fertige Münzproduktion gefunden.