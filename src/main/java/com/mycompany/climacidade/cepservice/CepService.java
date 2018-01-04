/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.cepservice;

import com.mycompany.climacidade.Cidade;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Lucas
 */
public interface CepService {
    public Cidade getCidade(String cep) throws IOException, MalformedURLException;
    
}
