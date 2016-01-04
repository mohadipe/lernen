An diesem Projekt arbeite ich ersteinmal nicht weiter. 
Ich habe hier ein wenig mit UI-Automatisierung herum gespielt.
Das ist mit dem java.awt.Robot recht einfach zu machen.

Mein Use Case war das Spiel FoE. Hier gestalltet sich die Grafik-Erkennung als 
problematisch. Das hat mich zuviel Zeit gekostet und nicht so recht weiter gebracht.

In diesem Projekt ist mir wieder aufgefallen das ich mich schwer getan habe die 
BDD-Tests zu formulieren befor ich den Programmcode schreibe.
Da muss ich mich mehr dazu zwingen.
Mit den Unit-Tests konnte ich aber einige Ideen schnell ausprobieren ohne den Robot
starten zu müssen.
Mockito erweist sich als sehr praktisch um die Mausklicks nicht in echt auszuführen während
der Tests.
Die Schreibweise von Mockito empfinde ich auch als gut lesbar.
Der static Import von Mockito nervt hier muss ich die IDE besser nutzen damit diese
Imports auch automatisch organisert werden.

Die BDD Tests im gleichen Projekt zu haben wie den produktiven Code hat den Nachteil 
das man nicht unbedingt eine Schnittstelle "designen" muss.
In Zukunft werde ich BDD-Tests in einem extra Projekt pflegen. Dadurch zwinge ich mich
dem produktivem Projekt eine Schnittstelle zu geben. 