/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.dao.CidadeDAO;
import com.mycompany.climacidade.dao.CidadeDAOMySQL;

/**
 *
 * @author Lucas
 */
public class CidadeFactory {
    /**
     * 
     * @param banco
     * @return
     * @throws Exception 
     */
    public static CidadeDAO getBanco (String banco) throws Exception{
        try{
            if ("MySQL".equals(banco)){
                return new CidadeDAOMySQL();
            } else {
                throw new Exception("Banco inválido!");
            }
        } catch (Exception e){
            throw e;
        }
    }
}
