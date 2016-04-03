package installer;

import config.PropertieEnums;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Anwendung {

    public void starten(final Properties properties) {
        try {
            final Path installPath = Paths.get(properties.getProperty(PropertieEnums.installPath.toString()));
            final Path exeIncPath = installPath.resolve(properties.getProperty(PropertieEnums.exeDatei.toString()));

            new ProcessBuilder(exeIncPath.toString()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
