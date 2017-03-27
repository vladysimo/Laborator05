package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 9 - default constructor
    public StartedServiceBroadcastReceiver() {

    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String information = null;
        if (action.equals(Constants.ACTION_STRING)) {
            information = intent.getStringExtra(Constants.DATA);
        }
        if (action.equals(Constants.ACTION_INTEGER)) {
            information = String.valueOf(intent.getIntExtra(Constants.DATA, 0));
        }
        if (action.equals(Constants.ACTION_ARRAY_LIST)) {
            information = intent.getStringArrayListExtra(Constants.DATA).toString();
        }

        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
        if (messageTextView != null)
            messageTextView.setText(messageTextView.getText() + "\n" + information);
        else {
            Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, information);
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(startedServiceActivityIntent);
        }
    }
}
