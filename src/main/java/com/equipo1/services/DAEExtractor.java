/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.services;

import com.equipo1.dto.CredentialData;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author XPxTBxLLX
 */
public class DAEExtractor {
    
    public CredentialData extractData(String QRurl) throws IOException, Exception{
        
        if(!QRurl.contains("servicios.dae.ipn.mx")){
            throw new Exception ("QR invalido");
        }
        
        Document doc = Jsoup.connect(QRurl).get();
        
        CredentialData data = new CredentialData();
        
        data.setFullName(doc.select(".nombre").text());
        data.setBoleta(doc.select(".boleta").text());
        data.setCarrer(doc.select(".carrera").text());
        data.setCurp(doc.select(".curp").text());
        
        return data;
    }
}
