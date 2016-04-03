package installer;

import config.PropertieEnums;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

/**
 * Von Wo: https://repo.maven.apache.org/maven2/
 * Was: maven-groupid:artifactid:version:datei-name z.B.: org/mockito/mockito-all/1.10.19/
 * - groupid: org/mockito/
 * - artifactid: mockito-all
 * - version: 1.10.19
 * - datei-name: mockito-all-1.10.19.jar
 */
public class Download {

    public void download(final Properties properties) throws IOException {

        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

        final StringBuffer url = new StringBuffer();
        url.append(properties.getProperty(PropertieEnums.repo.toString()))
                .append(properties.getProperty(PropertieEnums.groupid.toString()))
                .append(properties.getProperty(PropertieEnums.artifactid.toString()))
                .append(properties.getProperty(PropertieEnums.version.toString()))
                .append(properties.getProperty(PropertieEnums.datei.toString()));
        final URL website = new URL(url.toString());
        final ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        final FileOutputStream fos = new FileOutputStream(properties.getProperty(PropertieEnums.downloadTarget.toString()) + properties.getProperty(PropertieEnums.datei.toString()));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        rbc.close();
    }
}
