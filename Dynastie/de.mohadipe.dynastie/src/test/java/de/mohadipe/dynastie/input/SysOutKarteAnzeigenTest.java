package de.mohadipe.dynastie.input;

import org.junit.Test;

import de.mohadipe.dynastie.RandomServiceImpl;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.FuenfMalFuenfKarte;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.output.SysOutKarteAnzeige;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;

public class SysOutKarteAnzeigenTest {

	@Test
	public void karteAnzeigen() {
		Karte fuenfMalFuenfKarte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(fuenfMalFuenfKarte);
		sysOutKarteAnzeige.anzeigen();
	}

	@Test
	public void karteMitAiEinheitenAnzeigen() {
		Karte fuenfMalFuenfKarte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		Einheit einheit = new Infanterie();
		einheit.setSpieler(new ComputerSpieler("KI"));
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), einheit);
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(fuenfMalFuenfKarte);
		sysOutKarteAnzeige.anzeigen();
	}

	@Test
	public void karteMitPEinheitenAnzeigen() {
		Karte fuenfMalFuenfKarte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		Einheit einheit = new Infanterie();
		einheit.setSpieler(new ComputerSpieler("KI"));
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), einheit);
		Einheit einheit2 = new Infanterie();
		einheit2.setSpieler(new MenschSpieler());
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(4, 3), einheit2);
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(fuenfMalFuenfKarte);
		sysOutKarteAnzeige.anzeigen();
	}
}
