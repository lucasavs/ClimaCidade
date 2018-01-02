/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.dao.TemperaturaDAO;
import com.mycompany.climacidade.dao.TemperaturaDAOMySQL;

/**
 *
 * @author Lucas
 */
public class TemperaturaFactory {
    public static TemperaturaDAO getBanco (String banco) throws Exception{
        try{
            if ("MySQL".equals(banco)){
                return new TemperaturaDAOMySQL();
            } else {
                throw new Exception("Banco inválido!");
            }
        } catch (Exception e){
            throw e;
        }
    }
}
