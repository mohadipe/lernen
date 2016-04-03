package installer;

import config.PropertieEnums;
import util.DeleteFolder;
import util.EntpackeZip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class Installation {

    public void ersetzen(final Properties properties) {
        try {
            final Path installPath = Paths.get(properties.getProperty(PropertieEnums.installPath.toString()));
            final Path downloadPath = Paths.get(properties.getProperty(PropertieEnums.downloadTarget.toString()));
            final Path datei = Paths.get(properties.getProperty(PropertieEnums.datei.toString()));

            final File file = new File(properties.getProperty(PropertieEnums.installPath.toString()));
            if (file.exists()) {
                DeleteFolder.deleteFolder(file);
            }

            Files.createDirectory(installPath);

            final Path quelle = downloadPath.resolve(datei);
            final Path ziel = installPath.resolve(datei);

            // TODO löschen der "datei" fehlt noch, wie mit evtl. vorhandenen Locks umgehen?
            // TODO -> move vs. copy
            Files.copy(quelle, ziel, StandardCopyOption.REPLACE_EXISTING);

            final String exeDatei = properties.getProperty(PropertieEnums.exeDatei.toString());
            if (exeDatei != null) {
                EntpackeZip.entpackeZip(ziel, installPath);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
