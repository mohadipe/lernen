Feature: Einfache Addition von natürlichen Zahlen inkl. 0.

Scenario: Addition von zwei Zahlen.
	Given Der Summand "2".
	And Der Summand "3".
	When Die Summanden addiert werden.
	Then Die Summe beträgt "5".