/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.services;

import com.equipo1.dto.CredentialData;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author XPxTBxLLX
 */
public class DAEExtractor {
    
     public CredentialData extractData(String QRurl) throws Exception {

        if (!QRurl.contains("servicios.dae.ipn.mx")) {
            throw new Exception("QR invalido");
        }

        // 1. Crear el TrustManager que acepta cualquier certificado
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };

        // 2. Crear e inicializar el SSLContext con ese TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        // 3. Aplicar globalmente (única forma en Jsoup 1.13.1)
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        // 4. Conectar con Jsoup — sin .sslSocketFactory() porque 1.13.1 no lo tiene
        Document doc = Jsoup.connect(QRurl)
                .ignoreHttpErrors(true)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .get();

        // Debug temporal — quitar cuando funcione
        System.out.println("=== HTML recibido ===");
        System.out.println(doc.html().substring(0, Math.min(2000, doc.html().length())));
        System.out.println("=====================");

        String fullname = doc.select(".nombre").text();
        String boleta   = doc.select(".boleta").text();
        String carrer   = doc.select(".carrera").text(); // ojo: era ".carrer" antes, corregido
        String curp     = doc.select(".curp").text();

        if (fullname.isEmpty() || boleta.isEmpty()) {
            throw new Exception("QR valido pero datos no encontrados. Revisa los logs.");
        }

        CredentialData data = new CredentialData();
        data.setFullName(fullname);
        data.setBoleta(boleta);
        data.setCarrer(carrer);
        data.setCurp(curp);

        return data;
    }
}
