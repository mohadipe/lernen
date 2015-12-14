package de.mohadipe.ui.test.robot;

import java.util.Date;
import java.util.logging.Logger;

import de.mohadipe.ui.test.robot.foe.FoEUIRobot;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class RobotStart {
	private static Logger logger = Logger.getLogger("de.mohadipe.ui.test.robot.RobotStart");
	
	public static void main(String[] args) {
		try {
			if (args == null || args.length == 0) {
				logger.severe("Programm Argument fehlt.");
				logger.severe("Verzeichnis indem das JAR liegt angeben.");
				return;
			}
			String programmRootVerzeichnis = args[0];
			GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(false);
			grafikDateiPfadeService.setProgrammRoot(programmRootVerzeichnis);
			FoEUIRobot foERobot = new FoEUIRobot();
			foERobot.setGrafikPathService(grafikDateiPfadeService);
			Long waitAfterRestart = 0L;
			while (true) {
				Thread.sleep(waitAfterRestart);
				logger.info("WaitAfterRestart: " + waitAfterRestart);
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
				logger.info("Restart in: " + waitAfterRestart + " ms");
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
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
