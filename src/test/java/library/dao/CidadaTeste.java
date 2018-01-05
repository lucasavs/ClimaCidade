/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.dao;

import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.CidadeFactory;
import com.mycompany.climacidade.dao.CidadeDAO;
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
public class CidadaTeste {
    
    Cidade cidade;
    CidadeDAO cidadeDAO;
    
    public CidadaTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cidade = new Cidade("São José dos testes");
        try {
            cidadeDAO = CidadeFactory.getCidadeDAO("MySQL");
        } catch (Exception ex) {
            System.out.println("Erro ao carregar o cidadeDAO!!");
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void insereCidade(){
        cidadeDAO.createCidade(cidade); //se der algo de errado ela irá disparar um erro
    }
    
    @Test
    public void getCidade(){
        Cidade cidadeBanco = cidadeDAO.getCidade(cidade.getNome());
        assertEquals(cidade.getNome(),cidadeBanco.getNome());
    }
    
    @Test(expected = Exception.class)
    public void deleteCidadeFail() throws Exception{
        Cidade cidadeNaoExistete = new Cidade("Cidade muito estranha que com certeza não tem o nome no banco");
        cidadeDAO.deleteCidade(cidadeNaoExistete);
    }
    
    @Test
    public void deleteCidade() throws Exception{
        cidadeDAO.deleteCidade(cidade);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
