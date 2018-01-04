/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.cepservice.CepService;
import com.mycompany.climacidade.cepservice.CepServiceViaCep;

/**
 *
 * @author Lucas
 */
public class CepServiceFactory {
    public static CepService getCepService(String nomeCepService) throws Exception{
        try{
            if ("ViaCep".equals(nomeCepService)){
                return new CepServiceViaCep();
            } else {
                throw new Exception("CepService inválido!");
            }
        } catch (Exception e){
            throw e;
        }
    }
}
