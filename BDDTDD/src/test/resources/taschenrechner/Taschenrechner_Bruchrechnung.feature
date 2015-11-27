Feature: Rechenoperationen mit Brüchen.

Scenario: Zwei Brüche mit gleichem Nenner addieren.
	Given Der Bruch "2/3".
	And Der Bruch "1/3".
	When Die Brüche addiert werden.
	Then Ist die Summe der Brüche "3/3".
	
Scenario: Einen Bruch kürzen.
	Given Der Bruch "6/8".
	When Der Bruch gekürzt wird.
	Then Ergibt dies den Bruch "3/4".