package installer;

import config.PropertieEnums;
import org.junit.Test;
import util.TestHelper;

import java.util.Properties;

public class AnwendungTest {

    @Test
    public void startenErfolgreichExe() {
        final Properties properties = new Properties();
        properties.put(PropertieEnums.installPath.toString(), "src/test/resources/exe/");
        properties.put(PropertieEnums.exeDatei.toString(), "test.exe");

        TestHelper.checkDateiVorhanden("src/test/resources/exe/test.exe");

        new Anwendung().starten(properties);
    }
}
