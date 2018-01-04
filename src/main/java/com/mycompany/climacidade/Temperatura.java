/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lucas
 */
public class Temperatura {
    
    private float graus;
    private LocalDateTime dataMedicao; //teste
    private int cidade_id;

    public int getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(int cidade_id) {
        this.cidade_id = cidade_id;
    }
    
    public Temperatura(float graus) {
        this.graus = graus;
        this.dataMedicao = LocalDateTime.now();
    }
    
    public float getGraus(){
        return this.graus;
    }
    
    public void setGraus(float graus){
        this.graus = graus;
    }
    
    /**
     * Retorna uma String com a data e hora da medição no formato "yyyy-MM-dd HH:mm:ss"
     * @return 
     */
    public String getDataMedicao(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dataMedicao.format(formatter);
    }
    
    public void setDataMedicao(String dataMedicao){
        this.dataMedicao = LocalDateTime.parse(dataMedicao);
    }

    public void setDataMedicao(LocalDateTime dataMedicao){
        this.dataMedicao = dataMedicao;
    }
    
    /**
     * Retorna uma string JSON com um objeto com a temperatura e a data da medição
     * @return 
     */
    @Override
    public String toString() {
        return "{\"date\":\"" + getDataMedicao() + "\" ,\"temperature\":\"" + getGraus() + "\" }" ;
    }
    
    
    
    
}
