/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import com.mycompany.climacidade.dao.CidadeDAO;
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
    
    /**
     * Retorna as temperaturas das últimas 30 horas da cidade informada
     * @param message
     * @return 
     */
    @GET
    @Path("/cities/{param}/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    public Response temperaturasCidade(@PathParam("param") String message){
        Cidade cidade;
        try {
            ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            cidade = climaFonte.getCidade(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidadeDAO.createCidade(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.getNome()).build();
    }
    
    /**
     * Cadastra uma nova cidade para ter a temperatura monitorada.
     * @param message
     * @return 
     */
    @POST
    @Path("/cities/{param}")
    @Produces("text/plain")
    public Response salvarCidade(@PathParam("param") String message) {
        Cidade cidade;
        try {
            ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            cidade = climaFonte.getCidade(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidadeDAO.createCidade(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.getNome()).build();
    }

    /**
     * Remove uma cidade do monitoramento.
     * @param message
     * @return 
     */
    @DELETE
    @Path("/cities/{param}")
    @Produces("text/plain")
    public Response deletarCidade(@PathParam("param") String message) {
        Cidade cidade;
        try {
            ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            cidade = climaFonte.getCidade(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidadeDAO.deleteCidade(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.getNome()).build();
    }

    /**
     * Apaga o histórico de temperaturas da cidade.
     * @param message
     * @return 
     */
    @DELETE
    @Path("/cities/{param}/temperatures")
    @Produces("text/plain")
    public Response deletarTemperaturasCidade(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }

    /**
     * Retorna a lista paginada das cidades em ordem decrescente da última temperatura registrada.
     * @param message
     * @return 
     */
    @GET
    @Path("/temperatures")
    @Produces("text/plain")
    public Response Temperaturas(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
    }
}
