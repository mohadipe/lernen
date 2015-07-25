package de.mohadipe.dynastie.input;

import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.output.SysOutKarteAnzeige;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;

public class SysOutKarteAnzeigenTest {
	private Karte karte;

	@Before
	public void setUp() {
		Konfiguration spielKonfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(spielKonfiguration);
		karte = spielKonfiguration.getKarte();
	}

	@Test
	public void karteAnzeigen() {
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(karte);
		sysOutKarteAnzeige.anzeigen();
	}

	@Test
	public void karteMitAiEinheitenAnzeigen() {
		Einheit einheit = new Infanterie();
		einheit.setSpieler(new ComputerSpieler("KI"));
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), einheit);
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(karte);
		sysOutKarteAnzeige.anzeigen();
	}

	@Test
	public void karteMitPEinheitenAnzeigen() {
		Einheit einheit = new Infanterie();
		einheit.setSpieler(new ComputerSpieler("KI"));
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), einheit);
		Einheit einheit2 = new Infanterie();
		einheit2.setSpieler(new MenschSpieler());
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(4, 3), einheit2);
		SysOutKarteAnzeige sysOutKarteAnzeige = new SysOutKarteAnzeige(karte);
		sysOutKarteAnzeige.anzeigen();
	}
}
