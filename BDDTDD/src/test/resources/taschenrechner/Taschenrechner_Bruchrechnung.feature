Feature: Rechenoperationen mit Brüchen.

Scenario: Zwei Brüche mit gleichem Nenner addieren.
	Given Der Bruch "2/3".
	And Der Bruch "1/3".
	When Die Brüche addiert werden.
	Then Ist die Summe der Brüche "3/3".