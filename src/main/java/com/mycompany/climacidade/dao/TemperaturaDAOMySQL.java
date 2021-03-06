/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.dao;

import com.mycompany.climacidade.Cidade;
import com.mycompany.climacidade.Temperatura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class TemperaturaDAOMySQL implements TemperaturaDAO{

    private final Connection connection;
    private static final String USUARIO_MYSQL = "root";
    private static final String SENHA_MYSQL = "root";
    private static final String INTERVALO_RECENTES = "30 HOUR";

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public TemperaturaDAOMySQL() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        /* Aqui registra */
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //TODO: Entender melhor essa questão de registro de driver
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/climacidade", USUARIO_MYSQL, SENHA_MYSQL);
        //System.out.println("Conectado com sucesso!");
    }
    
   @Override
    public void createTemperatura(Temperatura temperatura) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO TEMPERATURA (grau,cidade_id) VALUES (" + temperatura.getGraus()+ "," + temperatura.getCidade_id() +")");
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Temperatura> getTemperaturasRecentes(Cidade cidade) {
        List<Temperatura> temperaturas = new ArrayList<>();
        Temperatura temperatura;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM temperatura WHERE cidade_id = " + cidade.getId() + " AND data_hora_registro > DATE_SUB(NOW(), INTERVAL " + INTERVALO_RECENTES +")");
            
            while(resultSet.next()){
                temperatura = new Temperatura(resultSet.getFloat("grau"));
                Timestamp dataMedicao = resultSet.getTimestamp("data_hora_registro");
                temperatura.setDataMedicao(dataMedicao.toLocalDateTime());
                temperaturas.add(temperatura);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temperaturas;
    }

    @Override
    public void deleteTemperaturas(Cidade cidade) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM temperatura WHERE cidade_id = " + cidade.getId());
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
