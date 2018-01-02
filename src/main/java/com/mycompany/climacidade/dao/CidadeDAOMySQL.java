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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class CidadeDAOMySQL implements CidadeDAO {

    private final Connection connection;
    private static final String USUARIO_MYSQL = "root";
    private static final String SENHA_MYSQL = "root";

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public CidadeDAOMySQL() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        /* Aqui registra */
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //TODO: Entender melhor essa questão de registro de driver
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/climacidade", USUARIO_MYSQL, SENHA_MYSQL);
        //System.out.println("Conectado com sucesso!");
    }

    /**
     *
     * @param cidade
     */
    @Override
    public void createCidade(Cidade cidade) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO CIDADE (nome) VALUES (" + cidade.getNome() + ")");
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param cidade
     */
    @Override
    public void deleteCidade(Cidade cidade) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM CIDADE WHERE nome = " + cidade.getNome());
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteTemperaturas(Cidade cidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Temperatura> getTemperatura(Cidade cidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> getCidades() {
        Cidade cidade;
        List<Cidade> listaCidade = new ArrayList<Cidade>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cidade");
            
            while(resultSet.next()){
                cidade = new Cidade(resultSet.getString("nome"));
                cidade.setId(resultSet.getInt("id"));
                listaCidade.add(cidade);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCidade;
    }

}
