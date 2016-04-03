package util;

import org.junit.Assert;

import java.io.File;

public class TestHelper {

    public static void checkDateiNichtVorhanden(final String dateiName) {
        final File file = new File(dateiName);
        Assert.assertFalse(file.exists());
    }

    public static void checkDateiVorhanden(final String dateiName) {
        final File file = new File(dateiName);
        Assert.assertTrue(file.exists());
    }
}
