/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class Temperatura {
    
    private float graus;
    private LocalDate dataMedicao; //teste
    private int cidade_id;

    public int getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(int cidade_id) {
        this.cidade_id = cidade_id;
    }
    
    public Temperatura(float graus) {
        this.graus = graus;
        this.dataMedicao = LocalDate.now();
    }
    
    public float getGraus(){
        return this.graus;
    }
    
    public void setGraus(float graus){
        this.graus = graus;
    }
    
    public String getDataMedicao(){
        return dataMedicao.toString();
    }

    @Override
    public String toString() {
        return "\"date\":\"" + dataMedicao + "\" ,\"temperature\":\"" + dataMedicao + "\" }" ;
    }
    
    
    
    
}
