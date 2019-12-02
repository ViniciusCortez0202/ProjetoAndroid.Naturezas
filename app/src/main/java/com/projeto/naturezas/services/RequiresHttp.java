package com.projeto.naturezas.services;

import android.database.Cursor;
import android.util.Log;
import android.webkit.ConsoleMessage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequiresHttp {

    private String response = null;
    private String urlReq = "http://projetoandroid-com.umbler.net";

    //verifica se o usuario tem login
    public String fazerLogin(String email, String senha) {

        try {
            URL url = new URL(this.urlReq + "/usuario/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String body = "email=" + email + "&senha=" + senha;

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(body);
            out.flush();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    public String buscarRaking() {


        try {
            URL url = new URL(urlReq + "/usuario/ranking");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String criarContaUsuario(String nome, String email, String senha, String celular) {


        try {
            URL url = new URL(urlReq);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String body = "nome=" + nome + "&email=" + email + "&senha=" + senha + "&numero=" + celular;

            conn.getOutputStream().write(body.getBytes());

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String atualizarPontuacao(String id, String pontuacao) {

        try {
            URL url = new URL(urlReq + "/usuario/atualizarpontos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String body = "id=" + id + "&pontuacao=" + pontuacao;

            conn.getOutputStream().write(body.getBytes());

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


}
