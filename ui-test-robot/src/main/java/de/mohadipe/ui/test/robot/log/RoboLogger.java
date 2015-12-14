package de.mohadipe.ui.test.robot.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RoboLogger {
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	static public void setup() throws SecurityException, IOException {
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		fileTxt = new FileHandler("Logging.txt");
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}
}
