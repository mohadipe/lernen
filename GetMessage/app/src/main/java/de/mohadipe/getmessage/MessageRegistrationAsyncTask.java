package de.mohadipe.getmessage;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageRegistrationAsyncTask extends AsyncTask<Void, Void, String> {

    private Context context;
    public MessageRegistrationAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            //Keep a socket open to listen to all the UDP trafic that is destined for this port
            DatagramSocket socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (true) {
                Logger.getLogger(MessageRegistrationAsyncTask.class.getName()).log(Level.SEVERE, ">>>Ready to receive broadcast packets!", "");

                //Receive a packet
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                socket.receive(packet);

                //Packet received
                Logger.getLogger(MessageRegistrationAsyncTask.class.getName()).log(Level.SEVERE, ">>>Discovery packet received from: " + packet.getAddress().getHostAddress(), "");
                Logger.getLogger(MessageRegistrationAsyncTask.class.getName()).log(Level.SEVERE, ">>>Packet received; data: " + new String(packet.getData()), "");

                //See if the packet holds the right command (message)
                String msg = new String(packet.getData()).trim();
                if (msg.equals("DISCOVER_FUIFSERVER_REQUEST")) {
//                    Intent newMessageIntent = new Intent(context, MessageIntentService.class);
//                    newMessageIntent.putExtra("NEW_MESSAGE_RECEIVED", msg);
//                    MessageBroadcastReceiver.startWakefulService(context, newMessageIntent);

                    Intent newPositionsIntent = new Intent("NEW_POSITIONS_FOUND");
                    newPositionsIntent.putExtra("NEW_POSITIONS_COUNTER", msg);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(newPositionsIntent);

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageRegistrationAsyncTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        Logger.getLogger("EMPFANGEN:").log(Level.INFO, s);
    }
}
