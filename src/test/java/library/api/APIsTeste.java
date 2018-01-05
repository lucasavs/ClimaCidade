/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.api;

import com.mycompany.climacidade.CepServiceFactory;
import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.ClimaFonteFactory;
import com.mycompany.climacidade.cepservice.CepService;
import com.mycompany.climacidade.climafonte.ClimaFonte;
import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class APIsTeste {
    
    private ClimaFonte climaFonte;
    private CepService cepService;
    
    public APIsTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            climaFonte = ClimaFonteFactory.getClimaFonte("openWeatherMap");
        } catch (Exception ex) {
            System.out.println("Erro ao carregar a classe de clima!");
        }
        
        try {
            cepService = CepServiceFactory.getCepService("ViaCep");
        } catch (Exception e){
            System.out.println("Erro ao carregar a classe de cep!");
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testeNomeCidade() throws IOException{
        Cidade cidade = climaFonte.getCidade("rio de janeiro");
        assertEquals("Rio de Janeiro",cidade.getNome());
    }
    
    @Test
    public void testeCepCidade() throws IOException{
        Cidade cidade = cepService.getCidade("22221011");
        assertEquals("Rio de Janeiro",cidade.getNome());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

    
