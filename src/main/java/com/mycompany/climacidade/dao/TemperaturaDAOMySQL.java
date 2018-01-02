/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.climacidade.dao;

import com.mycompany.climacidade.Temperatura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    
}
