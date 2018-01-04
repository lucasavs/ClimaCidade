/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import com.mycompany.climacidade.climafonte.ClimaFonte;

/**
 *
 * @author Lucas
 */
public class ClimaFonteFactory {
    /**
     * Retorna um novo objeto de ClimaFonte
     * @param climaFonte
     * @return
     * @throws Exception 
     */
    public static ClimaFonte getClimaFonte (String climaFonte) throws Exception{
        try{
            if ("openWeatherMap".equals(climaFonte)){
                return new ClimaFonteopenweathermap();
            } else {
                throw new Exception("Api de clima fonte inválida!");
            }
        } catch (Exception e){
            throw e;
        }
    }
}
