package util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntpackeZipTest {

    @Before
    public void setUp() {
        final File entpackt = new File("src/test/resources/entpackt");
        DeleteFolder.deleteFolder(entpackt);
        entpackt.mkdir();
    }

    @Test
    public void entpackeZipErfolgreich() throws IOException {
        final Path resolve = Paths.get("src/test/resources/zip/test.zip");
        final Path installPath = Paths.get("src/test/resources/entpackt");

        TestHelper.checkDateiNichtVorhanden("src/test/resources/entpackt/test.exe");
        TestHelper.checkDateiNichtVorhanden("src/test/resources/entpackt/test.txt");

        EntpackeZip.entpackeZip(resolve, installPath);

        TestHelper.checkDateiVorhanden("src/test/resources/entpackt/test.exe");
        TestHelper.checkDateiVorhanden("src/test/resources/entpackt/test.txt");
    }
}
