Feature: Starte Forge of Empire

Scenario: Forge of Empire starten
	Given Ein Screenshot mit FoE-Verknüpfung.
	When Ein doppel Klick auf die Verknüpfung gemacht wird.
	Then Öffnet sich ein Browser mit der Startseite von Forge of Empire.
	
Scenario: Forge of Empire spielen
	Given Die Startseite von Forge of Empire ist im Browser offen.
	When Der Spielen Button gedrückt wird.
	Then Öffnet sich die Auswahl des Servers.
	When Der Server Rugnir ausgewählt wird.
	Then Die Stadt des Spielers erscheint.