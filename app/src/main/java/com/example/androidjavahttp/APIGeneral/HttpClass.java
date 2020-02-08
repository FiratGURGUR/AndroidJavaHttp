package com.example.androidjavahttp.APIGeneral;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClass {

    public static String SendHttpPost(String URL, JSONArray array) {
        String don="";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                doTrustToCertificates();
            }
            java.net.URL connectURL = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(array.toString());
            os.flush();
            String conco = String.valueOf(conn.getResponseCode());
            InputStream is = conn.getInputStream();
            don=convertStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }
    public static String SendHttpPost(Activity activity, String URL, JSONObject obje) {
        String don="";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                doTrustToCertificates();
            }
            URL connectURL = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(obje.toString());
            os.flush();
            String conco = String.valueOf(conn.getResponseCode());
            InputStream is = conn.getInputStream();
            don=convertStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }
    public static void doTrustToCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
    public static String GetHttp(String URL) {
        String don="";
        try {
            URL connectURL = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            String conco = String.valueOf(conn.getResponseCode());
            InputStream is = conn.getInputStream();
            don=convertStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
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
    private static Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            int REQUIRED_SIZE=512;
            if(512!=-1)
                REQUIRED_SIZE=512;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            String aa=e.toString();
        }
        return null;
    }


    public static String SendHttpPostwithToken(String URL, JSONArray array,String token) {
        String don="";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                doTrustToCertificates();
            }
            java.net.URL connectURL = new URL(URL);
            HttpURLConnection   conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(array.toString());
            os.flush();
            String conco = String.valueOf(conn.getResponseCode());

            if (conn.getResponseCode() == 500){
                //token eski login'e dön
                return "";

            }
            InputStream is = conn.getInputStream();
            don=convertStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }
    public static String SendHttpPostwithToken(String URL, JSONObject obje, String token) {
        String don="";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                doTrustToCertificates();
            }
            URL connectURL = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(obje.toString());
            os.flush();
            String conco = String.valueOf(conn.getResponseCode());

            if (conn.getResponseCode() == 500){
                //token eski login'e dön
                return "";

            }

            InputStream is = conn.getInputStream();
            don=convertStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }








}
