/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
 
@Path("/")
public class Controller {
 
    @GET
    @Path("/hello/{param}")
    @Produces("text/plain")
    public Response getMsg(@PathParam("param") String message) {
        String output = "Hello " + message + "!";
        return Response.status(200).entity(output).build();
    }
    
    @GET
    @Path("/cities/{param}/temperatures")
    @Produces("text/plain")
    public Response temperaturasCidade(@PathParam("param") String message) {
        String aviso = "Não ainda implementado";
        return Response.status(200).entity(aviso).build();
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