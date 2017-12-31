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
    
    
}
