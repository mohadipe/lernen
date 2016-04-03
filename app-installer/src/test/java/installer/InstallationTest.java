package installer;

import config.PropertieEnums;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DeleteFolder;
import util.TestHelper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class InstallationTest {

    @Before
    public void setUp() throws IOException {
        final File leistung = new File("installDir");
        DeleteFolder.deleteFolder(leistung);
        leistung.mkdir();
    }

    @Test
    public void ersetzenErfolgreich() {
        final Properties properties = new Properties();
        properties.put(PropertieEnums.installPath.toString(), "installDir");
        properties.put(PropertieEnums.datei.toString(), "mockito-all-1.10.19.jar");
        properties.put(PropertieEnums.downloadTarget.toString(), "src/test/resources/download/");

        TestHelper.checkDateiNichtVorhanden("installDir/mockito-all-1.10.19.jar");

        new Installation().ersetzen(properties);

        TestHelper.checkDateiVorhanden("installDir/mockito-all-1.10.19.jar");
    }

    @Test
    public void entpackenErfolgreich() {
        final Properties properties = new Properties();
        properties.put(PropertieEnums.exeDatei.toString(), "test.exe");
        properties.put(PropertieEnums.installPath.toString(), "installDir");
        properties.put(PropertieEnums.downloadTarget.toString(), "src/test/resources/zip/");
        properties.put(PropertieEnums.datei.toString(), "test.zip");

        TestHelper.checkDateiNichtVorhanden("installDir/test.exe");
        TestHelper.checkDateiNichtVorhanden("installDir/test.txt");

        new Installation().ersetzen(properties);

        TestHelper.checkDateiVorhanden("installDir/test.exe");
        TestHelper.checkDateiVorhanden("installDir/test.txt");
    }
}
