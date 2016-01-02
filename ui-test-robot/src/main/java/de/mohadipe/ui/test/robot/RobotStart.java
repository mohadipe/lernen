package de.mohadipe.ui.test.robot;

import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Logger;

import de.mohadipe.ui.test.robot.foe.FoEAufgabenRobot;
import de.mohadipe.ui.test.robot.log.RoboLogger;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class RobotStart {
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		try {
			RoboLogger.setup();
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		if (args == null || args.length == 0) {
			logger.severe("Programm Argument fehlt.");
			logger.severe("Verzeichnis indem das JAR liegt angeben.");
			return;
		}
		String programmRootVerzeichnis = args[0];
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(
				false);
		grafikDateiPfadeService.setProgrammRoot(programmRootVerzeichnis);

		// Alle 5 Min schließt der Robot den Browser.
		// Wartet 3 Min
		// Danach Neustart des Robots
		try {
			FoEAufgabenRobot foERobot = new FoEAufgabenRobot();
			foERobot.setGrafikPathService(grafikDateiPfadeService);
			foERobot.setLaufZeitTimeOut(120000L);
			do {
				foERobot.sammleGoldUndWerkzeuge();
				Thread.sleep(240000L);
				foERobot.schliesseBrowser();
			} while (true);
		} catch (AWTException | InterruptedException e) {
			logger.severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
