/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.cepservice;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.climacidade.Cidade;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Lucas
 */
public class CepServiceViaCep implements CepService  {
    
    private static final String VIACEP_URL = "https://viacep.com.br/ws/";
    private static final String VIACEP_URL_COMPLEMENTO = "/json";
    
    /**
     * Retorna uma cidade com os dados obtidos na API de CEP. Retorna null caso não ache nenhuma cidade
     * @param cep
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    @Override
    public Cidade getCidade(String cep) throws IOException, MalformedURLException {
        String sURL = VIACEP_URL + cep + VIACEP_URL_COMPLEMENTO; //just a string
        
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        
        if(request.getResponseCode() == 404){
            return null;
        }
        
        JsonParser jp = new JsonParser(); 
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent(),"UTF-8")); 
        JsonObject rootobj = root.getAsJsonObject(); 
        
        String nomeCidade = rootobj.getAsJsonObject().get("localidade").getAsString();

        Cidade cidade = new Cidade (nomeCidade);
        return cidade;
        
    }
    
}
