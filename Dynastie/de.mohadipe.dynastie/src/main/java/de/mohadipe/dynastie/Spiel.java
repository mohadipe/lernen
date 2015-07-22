package de.mohadipe.dynastie;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.mohadipe.dynastie.input.SysoBewegungZielKoordinaten;
import de.mohadipe.dynastie.input.SysoEinheitAufstellenKoordinaten;
import de.mohadipe.dynastie.input.SysoEinheitenAufstellenMenu;
import de.mohadipe.dynastie.input.SysoKonfigurationsMenu;
import de.mohadipe.dynastie.input.SysoStartMenu;
import de.mohadipe.dynastie.output.SysOutSpielZug;
import de.mohadipe.dynastie.workflow.EinheitenAufstellen;
import de.mohadipe.dynastie.workflow.KonfigurationsMenu;
import de.mohadipe.dynastie.workflow.SpielWorkflow;
import de.mohadipe.dynastie.workflow.SpielZuege;
import de.mohadipe.dynastie.workflow.StartMenu;
import de.mohadipe.dynastie.workflow.UnbekannterSchritt;
import de.mohadipe.dynastie.workflow.Workflow;

public class Spiel {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DynastieModule());

		Workflow spielWorkflow = injector.getInstance(SpielWorkflow.class);
		init(spielWorkflow);
		boolean laeuft = true;
		while (laeuft) {
			int i = spielWorkflow.anzeigen();
			System.out.println("Sie möchten: " + i);
			if (i == 2) {
				System.out.println("Schön wars.");
				laeuft = false;
				continue;
			}
		}
	}

	private static void init(Workflow spielWorkflow) {
		spielWorkflow.addSchritt(1, new StartMenu(new SysoStartMenu()));
		spielWorkflow.addSchritt(10, new KonfigurationsMenu(spielWorkflow.getKonfiguration(), new SysoKonfigurationsMenu()));
		spielWorkflow.addSchritt(15, new EinheitenAufstellen(spielWorkflow.getKonfiguration(), spielWorkflow.getRandomService(), new SysoEinheitenAufstellenMenu(), new SysoEinheitAufstellenKoordinaten()));
		spielWorkflow.addSchritt(20, new SpielZuege(spielWorkflow.getKonfiguration(), spielWorkflow, new SysoBewegungZielKoordinaten(), new SysOutSpielZug()));
		spielWorkflow.addSchritt(999, new UnbekannterSchritt());
	}
}
