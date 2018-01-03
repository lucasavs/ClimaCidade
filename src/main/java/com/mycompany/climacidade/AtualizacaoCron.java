/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade;

/**
 *
 * @author Lucas
 */
import com.mycompany.climacidade.climafonte.ClimaFonteopenweathermap;
import com.mycompany.climacidade.dao.CidadeDAO;
import com.mycompany.climacidade.dao.TemperaturaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AtualizacaoCron implements ServletContextListener {

    private Thread t = null;
    private ServletContext context;
    private static final int TEMPO_ATUALIZACAO_TEMPERATURA = 600000; //dez minutos

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        t = new Thread() {
            //task
            @Override
            public void run() {
                List<Cidade> listaCidades;
                ClimaFonteopenweathermap climaFonte = new ClimaFonteopenweathermap();
                Temperatura temperatura;
                try {
                    while (true) {
                        CidadeDAO cidadeDAO = CidadeFactory.getBanco("MySQL");
                        TemperaturaDAO temperaturaDAO = TemperaturaFactory.getBanco("MySQL");
                        listaCidades = cidadeDAO.getCidades();
                        
                        for (Cidade cidade : listaCidades) {
                            temperatura = climaFonte.getTemperatura(cidade.getNome());
                            temperatura.setCidade_id(cidade.getId());
                            temperaturaDAO.createTemperatura(temperatura);
                        }
                        
                        System.out.println("Pesquisa concluida com sucesso. " + listaCidades.size() + " Cidades devem ter suas temperaturas atualizadas");
                        Thread.sleep(TEMPO_ATUALIZACAO_TEMPERATURA);
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                    Logger.getLogger(AtualizacaoCron.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    Logger.getLogger(AtualizacaoCron.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
        context = contextEvent.getServletContext();
        // you can set a context variable just like this
        context.setAttribute("TEST", "TEST_VALUE");
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        // context is destroyed interrupts the thread
        t.interrupt();
    }
}
