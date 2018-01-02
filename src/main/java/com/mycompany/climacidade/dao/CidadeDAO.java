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
public interface CidadeDAO {
    public void createCidade(Cidade cidade);
    public void deleteCidade(Cidade cidade);
    public void deleteTemperaturas(Cidade cidade);
    public List<Temperatura> getTemperatura (Cidade cidade);
    public List<Cidade> getCidades ();
}
