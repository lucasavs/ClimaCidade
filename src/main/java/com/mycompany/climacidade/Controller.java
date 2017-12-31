/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Controller {

    @GET
    @Path("/hello/{param}")
    @Produces("text/plain")
    public Response getMsg(@PathParam("param") String message) {

        String output = "Hello " + message + "!";
        return Response.status(200).entity(output).build();
    }
    /*
    @GET
    @Path("/cities/{param}/temperatures")
    @Produces("text/plain")
    public Response temperaturasCidade(@PathParam("param") String message) {
        String mensagem = "teste";
                
        try {
            ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            Cidade cidade = climaFonte.getCidade(message);
            mensagem = cidade.getNome();
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return Response.status(200).entity(mensagem).build();
    }
*/
    
    @GET
    @Path("/cities/{param}/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    public Response temperaturasCidade(@PathParam("param") String message) throws Exception{
        Cidade cidade;
        String objetoEmJson;
        try {
            ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            cidade = climaFonte.getCidade(message);
            
            //ObjectMapper mapper = new ObjectMapper();
            //objetoEmJson = mapper.writeValueAsString(cidade);
        } catch (Exception e) {
            throw e;
        }
        return Response.status(200).entity(cidade.toString()).build();
        //return cidade;
    }
    
    @POST
    @Path("/cities/{param}")
    @Produces("text/plain")
    public Response salvarCidade(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }

    @DELETE
    @Path("/cities/{param}")
    @Produces("text/plain")
    public Response deletarCidade(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }

    @DELETE
    @Path("/cities/{param}/temperatures")
    @Produces("text/plain")
    public Response deletarTemperaturasCidade(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }

    @GET
    @Path("/temperatures")
    @Produces("text/plain")
    public Response Temperaturas(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }
}
