/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.climafonte;

import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.Temperatura;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Lucas
 */
public interface ClimaFonteInterface {
    public Cidade getCidade(String cidade) throws IOException,MalformedURLException;
    public Temperatura getTemperatura(String cidade) throws IOException,MalformedURLException;
}
