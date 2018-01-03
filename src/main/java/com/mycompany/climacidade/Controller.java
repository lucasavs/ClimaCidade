/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import com.mycompany.climacidade.dao.CidadeDAO;
import com.mycompany.climacidade.dao.TemperaturaDAO;
import java.io.IOException;
import java.util.List;
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
    
    /**
     * Retorna as temperaturas das últimas 30 horas da cidade informada
     * @param message
     * @return 
     */
    @GET
    @Path("/cities/{param}/temperatures")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public Response temperaturasCidade(@PathParam("param") String message){
        Cidade cidade;
        try {
            cidade = getCidadeAPI(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidade = cidadeDAO.getCidade(cidade.getNome());
            
            if(cidade == null){
                throw new Exception("Cidade não encontrada!");
            }
            
            TemperaturaDAO temperaturaDAO = TemperaturaFactory.getBanco("MySQL");
            cidade.setTemperaturas(temperaturaDAO.getTemperaturasRecentes(cidade));
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.toString()).build();
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
        Cidade cidadeAntiga;
        try {
            cidade = getCidadeAPI(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidadeAntiga = cidadeDAO.getCidade(cidade.getNome());
            if(cidadeAntiga != null){
                throw new Exception("Cidade já cadastrada!");
            }
            cidadeDAO.createCidade(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.getNome() + " salva com sucesso!").build();
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
            cidade = getCidadeAPI(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidadeDAO.deleteCidade(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(cidade.getNome() + " deletada com sucesso!").build();
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
        Cidade cidade;
        try {
            cidade = getCidadeAPI(message);
            
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            cidade = cidadeDAO.getCidade(cidade.getNome());
            
            if(cidade == null){
                throw new Exception("Cidade não encontrada!");
            }
            
            TemperaturaDAO temperaturaDAO = TemperaturaFactory.getBanco("MySQL");
            temperaturaDAO.deleteTemperaturas(cidade);
            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        
        return Response.status(200).entity("Temperaturas de " + cidade.getNome() + " deletadas com sucesso!").build();
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
        try{
            CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
            List<Cidade> cidades = cidadeDAO.getTemperaturasAtuais();
            return Response.status(200).entity(cidades.toString()).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }        
    }
    
    private Cidade getCidadeAPI(String nome) throws IOException, Exception{
        Cidade cidade;
        ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
            cidade = climaFonte.getCidade(nome);
            
            if(cidade == null){
                throw new Exception("Cidade não encontrada na API de climas!");
            }
        return cidade;
    }
}
