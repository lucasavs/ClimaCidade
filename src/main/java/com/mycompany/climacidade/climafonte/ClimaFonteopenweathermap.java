/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.climafonte;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.Temperatura;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class ClimaFonteopenweathermap implements ClimaFonteInterface {

    @Override
    public Cidade getCidade(String nomeBusca) throws IOException, MalformedURLException {
        String sURL = "http://api.openweathermap.org/data/2.5/find?q=" + nomeBusca +"&units=metric&appid=bd4e72aabc90d2656d325985bee2e76f"; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        //zipcode = rootobj.get("city").getAsString(); //just grab the zipcode
        String nomeCidade = rootobj.getAsJsonArray("list").get(0).getAsJsonObject().get("name").toString();
        float graus = rootobj.getAsJsonArray("list").get(0).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsFloat();
        
        Cidade cidade = new Cidade (nomeCidade);
        Temperatura temperatura = new Temperatura(graus);
        List<Temperatura> temperaturas = new ArrayList<>();
        temperaturas.add(temperatura);
        cidade.setTemperaturas(temperaturas);
        return cidade;
    }

}
