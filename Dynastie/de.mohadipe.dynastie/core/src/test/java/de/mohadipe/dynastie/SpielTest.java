package de.mohadipe.dynastie;

import org.junit.Test;

import de.mohadipe.dynastie.input.DummyBewegungZielKoordinaten;
import de.mohadipe.dynastie.input.DummyEinheitAufstellenKoordinaten;
import de.mohadipe.dynastie.input.DummyEinheitenAufstellenMenu;
import de.mohadipe.dynastie.input.DummyKonfigurationsMenu;
import de.mohadipe.dynastie.input.DummyStartMenu;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.logik.model.Koordinate;
import de.mohadipe.dynastie.output.SysOutSpielZug;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.workflow.EinheitenAufstellen;
import de.mohadipe.dynastie.workflow.KonfigurationsMenu;
import de.mohadipe.dynastie.workflow.SpielWorkflow;
import de.mohadipe.dynastie.workflow.SpielZuege;
import de.mohadipe.dynastie.workflow.StartMenu;
import de.mohadipe.dynastie.workflow.UnbekannterSchritt;
import de.mohadipe.dynastie.workflow.WorkflowSchritteKonfiguration;

public class SpielTest {

	@Test
	public void spielDurchlaufen() {
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.addSpieler(new ComputerSpieler("KI1"));
		spielKonfiguration.addSpieler(new ComputerSpieler("KI2"));
		RandomService randomService = new RandomServiceImpl();
		SpielWorkflow spielWorkflow = new SpielWorkflow(spielKonfiguration, randomService, new WorkflowSchritteKonfiguration());
		spielWorkflow.addSchritt(1, new StartMenu(new DummyStartMenu(20)));
		spielWorkflow.addSchritt(20, new KonfigurationsMenu(spielKonfiguration, new DummyKonfigurationsMenu(30)));
		Koordinate startKoordinate = new ZweiDimensionaleKoordinate(4, 5);
		spielWorkflow.addSchritt(30, new EinheitenAufstellen(spielKonfiguration, randomService, new DummyEinheitenAufstellenMenu(40), new DummyEinheitAufstellenKoordinaten(startKoordinate)));
		spielWorkflow.addSchritt(40, new SpielZuege(spielKonfiguration, spielWorkflow, new DummyBewegungZielKoordinaten(), new SysOutSpielZug()));
		spielWorkflow.addSchritt(999, new UnbekannterSchritt());
		boolean laeuft = true;
		while (laeuft) {
			int i = spielWorkflow.anzeigen();
			System.out.println("Sie moechten: " + i);
			if (i == 2) {
				System.out.println("Schoen wars.");
				laeuft = false;
				continue;
			}
		}
	}
}
