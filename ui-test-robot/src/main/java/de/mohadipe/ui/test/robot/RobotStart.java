package de.mohadipe.ui.test.robot;

import java.util.Date;

import de.mohadipe.ui.test.robot.foe.FoEUIRobot;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class RobotStart {

	public static void main(String[] args) {
		try {
			String programmRootVerzeichnis = args[0];
			if (programmRootVerzeichnis == null) {
				System.out.println("Programm Argument fehlt.");
				System.out.println("Verzeichnis indem das JAR liegt angeben.");
				return;
			}
			GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(false);
			grafikDateiPfadeService.setProgrammRoot(programmRootVerzeichnis);
			FoEUIRobot foERobot = new FoEUIRobot();
			foERobot.setGrafikPathService(grafikDateiPfadeService);
			Long waitAfterRestart = 0L;
			while (true) {
				Thread.sleep(waitAfterRestart);
				System.out.println("WaitAfterRestart: " + waitAfterRestart);
				long startZeit = new Date().getTime();
				foERobot.starteFoEBrowser();
				Thread.sleep(5000L);
				foERobot.spieleFoE();
				Thread.sleep(2000L);
				foERobot.waehleServerRugnir();
				Thread.sleep(25000L);
				foERobot.menuesBestaetigen();
				Thread.sleep(1000L);
				do {
					foERobot.holeMuenzenAb();
//					foERobot.holeWerkzeugAb();
//					foERobot.oeffneProduktion();
//					foERobot.guteHufeisenProduzieren();
					if (foERobot.beendePopUp()) {
						// Abweichung ist gedacht für LebkuchenHaus, klick öffnet "dahinter" liegendes Gebäude
						// diese beiden Schleifen müssen raus aus der Main Methode!!!
						foERobot.erhoeheAbweichung();
					} else {
						foERobot.resetAbweichung();
					}
				} while (restart(startZeit, foERobot));
				waitAfterRestart = Long.valueOf(600000L);
				System.out.println("Restart in: " + waitAfterRestart + " ms");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean restart(long startZeit, FoEUIRobot foERobot) {
		if (mehrAlsEineMinutenVergangen(startZeit)) {
			foERobot.beendeBrowser();
			return false;
		}
		return true;
	}

	private static boolean mehrAlsEineMinutenVergangen(long startZeit) {
		long aktuelleZeit = new Date().getTime();
		return aktuelleZeit - startZeit > 300000;
	}
}
