package installer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Start {

    public static void main(String[] args) {
        try {
            final Properties properties = new Properties();
            final BufferedInputStream stream = new BufferedInputStream(new FileInputStream("config.properties"));
            properties.load(stream);
            stream.close();

            new Download().download(properties);

            new Installation().ersetzen(properties);

            new Anwendung().starten(properties);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
