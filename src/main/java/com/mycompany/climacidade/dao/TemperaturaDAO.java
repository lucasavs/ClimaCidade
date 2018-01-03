/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.dao;

import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.Temperatura;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface TemperaturaDAO {
    public void createTemperatura(Temperatura temperatura);
    public List<Temperatura> getTemperaturasRecentes (Cidade cidade);
}
