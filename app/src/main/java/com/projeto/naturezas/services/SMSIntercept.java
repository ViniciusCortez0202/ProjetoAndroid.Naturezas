package com.projeto.naturezas.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.projeto.naturezas.R;

import java.nio.charset.CharacterCodingException;

public class SMSIntercept extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
// Get the SMS message.
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);

                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message to show.
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                String partesMensagem[] = strMessage.split(" ");
                String numeroConfirmacaoMensagem = partesMensagem[8].substring(0, 4);
               //Valida o numero para continuar
                SharedPreferences sp = context.getSharedPreferences
                        (context.getString(R.string.numero_confirmacao), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String numeroConfirmacao = sp.getString("numeroConfirmacao", "Número não encontrado");

                if(numeroConfirmacao.equalsIgnoreCase(numeroConfirmacaoMensagem)){
                    Toast.makeText(context, "Número de confirmação aceito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Erro ao confirmar número", Toast.LENGTH_LONG);
                }

            }
        }
    }
}
