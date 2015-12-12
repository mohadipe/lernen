Feature: Bewegung einer Einheit

Scenario: Eine Einheit vertikal mit maximaler Reichweite bewegen.
	Given Eine Karte mit "5" mal "5" Feldern.
	And Eine Einheit mit Reichweite "2".
	And Die Einheit befindet sich auf Feld "2" "3".
	When Die Einheit wird "2" Felder vertikal nach Norden bewegt.
	Then Befindet sich die Einheit auf Feld "2" "5".