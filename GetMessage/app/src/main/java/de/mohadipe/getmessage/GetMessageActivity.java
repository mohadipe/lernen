package de.mohadipe.getmessage;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

public class GetMessageActivity extends AppCompatActivity {

    private MessageBroadcastReceiver receiver = new MessageBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_message);

        new MessageRegistrationAsyncTask(this).execute();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                receiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                receiver, new IntentFilter("NEW_POSITIONS_FOUND"));
        super.onResume();
    }
}
