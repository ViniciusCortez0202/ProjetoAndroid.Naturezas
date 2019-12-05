package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.services.CriarConta;

public class LoginActivity extends AppCompatActivity{

    CriarConta l;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        l = new CriarConta(LoginActivity.this);

         usuario = new Usuario("Rocha", "kgu@g.com", "82996587987", "222");

        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.RECEIVE_SMS)) {
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        123);
            }
        } else {

            l.execute(usuario);
        }




       /*Intent in = new Intent(LoginActivity.this, Home.class);
        startActivity(in);*/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    l.execute(usuario);
                } else {
                    // Usuário não aceitou a permissão
                }

                break;
        }

    }
}

/*class Receive extends Application{

    private InterceptSMS smsBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Valor", "Entrou no receive");
        smsBroadcastReceiver = new InterceptSMS();
        registerReceiver(smsBroadcastReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));

    }

    @Override
    public void onTerminate() {
        unregisterReceiver(smsBroadcastReceiver);
        super.onTerminate();
    }
}
*/