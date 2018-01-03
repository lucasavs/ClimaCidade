/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import java.util.List;

/**
 *
 * @author Lucas
 */
public class Cidade {
    private List<Temperatura> temperaturas;
    private String nome;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cidade (String nome){
        this.nome = nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome (){
        return nome;
    }
    
    public List<Temperatura> getTemperaturas(){
        return temperaturas;
    }
    
    public void setTemperaturas(List<Temperatura> temperaturas){
        this.temperaturas = temperaturas;
    }
    
    /**
     * Retorna a cidade em formato JSON com suas temperaturas
     * @return 
     */
    public String toString(){
        //String stringArrayTemperaturas = ""; 
        /*
        for(Temperatura temperatura : temperaturas){
            stringArrayTemperaturas += "\"date\" : \"" + temperatura.getDataMedicao() + "\", \"temperature\" : \"" + temperatura.getGraus() + "\"";
        }
        */
        return "{\"name\":"+getNome()+", \"temperature\":" + temperaturasToString() + "}";
    }
    
    private String temperaturasToString (){
        return String.join(",", temperaturas.toString());
    }
}
