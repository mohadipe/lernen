package installer;

import config.PropertieEnums;
import org.junit.*;
import util.DeleteFolder;
import util.TestHelper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class DownloadTest {

    @Before
    public void setUp() {
        final File file = new File("src/test/resources/download");
        DeleteFolder.deleteFolder(file);
        file.mkdir();
    }

    @Test
    public void downloadErfolgreich() throws IOException {
        final Properties properties = new Properties();
        properties.put(PropertieEnums.repo.toString(), "https://repo.maven.apache.org/maven2/");
        properties.put(PropertieEnums.groupid.toString(), "org/mockito/");
        properties.put(PropertieEnums.artifactid.toString(), "mockito-all/");
        properties.put(PropertieEnums.version.toString(), "1.10.19/");
        properties.put(PropertieEnums.datei.toString(), "mockito-all-1.10.19.jar");
        properties.put(PropertieEnums.downloadTarget.toString(), "src/test/resources/download/");

        TestHelper.checkDateiNichtVorhanden("src/test/resources/download/mockito-all-1.10.19.jar");

        new Download().download(properties);

        TestHelper.checkDateiVorhanden("src/test/resources/download/mockito-all-1.10.19.jar");
    }
}
