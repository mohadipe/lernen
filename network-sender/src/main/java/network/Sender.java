package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wer auch immer im gleichen Netzwerk auf den Port 8888 hoert empfaengt diese Sendung.
 */
public class Sender {

    public void send() {
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = "DISCOVER_FUIFSERVER_REQUEST".getBytes();

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
                c.send(sendPacket);
                getLogger().log(Level.INFO, null, ">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
                getLogger().log(Level.WARNING, null, e);
            }

            // Broadcast the message over all the network interfaces
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package!
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
                        c.send(sendPacket);
                    } catch (Exception e) {
                        getLogger().log(Level.WARNING, null, e);
                    }

                    getLogger().log(Level.INFO, null, ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }

            getLogger().log(Level.INFO, null, ">>> Done looping over all network interfaces. Now waiting for a reply!");

            // TODO Antwort empfangen
//            empfangen(c);

            //Close the port!
            c.close();
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        }
    }

    private void empfangen(DatagramSocket dataGrSocket) throws IOException {
        //Wait for a response
        byte[] recvBuf = new byte[15000];
        DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
        dataGrSocket.receive(receivePacket);

        //We have a response
        getLogger().log(Level.INFO, null, ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

        //Check if the message is correct
        String message = new String(receivePacket.getData()).trim();
        if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
            //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
            getLogger().log(Level.INFO, null, message);
        }
    }

    private Logger getLogger() {
        return Logger.getLogger(Sender.class.getName());
    }
}
