Feature: Werkzeuge produzieren.

Scenario: In einer Schmiede Werkzeuge produzieren
	Given Ein Schmiede hat keine aktive Produktion.
	When Die Schmiede angeklickt wird.
	Then Öffnet sich das Produktionsfenster.
	When Gute Hufeisen produziert werden.
	Then Schliesst sich das Produktionsfenster.