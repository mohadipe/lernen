Feature: Spielzüge

Scenario: Computer macht diagonalen Zug.
	Given Ein Spielfeld mit 5 mal 5 Feldern
	And Eine Infanterie Einheit des Computers an x 1 y 1.
	When Der Computer einen Zug macht.
	Then Befindet sich die Einheit an x 2 y 2.