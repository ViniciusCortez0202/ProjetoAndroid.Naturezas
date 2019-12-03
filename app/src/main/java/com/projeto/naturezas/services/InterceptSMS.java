package com.projeto.naturezas.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class InterceptSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                }
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                if (pdusObj != null) {
                    for (Object aPdusObj : pdusObj) {

                        SmsMessage currentMessage;

                        if (Build.VERSION.SDK_INT >= 19) {
                            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                            currentMessage = msgs[0];
                        } else {
                            currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                        }

                        String message = currentMessage.getDisplayMessageBody();

                        // Vamos quebrar a mensagem por espaço
                        String protocol[] = message.split(" ");
                        Toast.makeText(context, message, Toast.LENGTH_LONG);
                    }
            }

        } catch (Exception e) {
            Log.e("TUTORIALANDROID", "Erro: " + e);
        }
    }
}