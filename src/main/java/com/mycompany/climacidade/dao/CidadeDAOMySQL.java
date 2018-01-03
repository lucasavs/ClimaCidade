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
    public List<Cidade> getTemperaturasAtuais() {
        Cidade cidade;
        Temperatura temperatura;
        List<Cidade> listaCidade = new ArrayList<>();
        List<Temperatura> temperaturas;
        String query = "SELECT t.*, c.nome\n" +
                        "FROM temperatura t\n" +
                        "INNER JOIN\n" +
                        "    (SELECT cidade_id, MAX(data_hora_registro) AS MaxDataHora\n" +
                        "    FROM temperatura\n" +
                        "    GROUP BY cidade_id) t1 \n" +
                        "ON t.cidade_id = t1.cidade_id \n" +
                        "AND t.data_hora_registro = t1.MaxDataHora\n" +
                        "INNER JOIN cidade c on c.id = t.cidade_id " +
                        "ORDER BY data_hora_registro DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                cidade = new Cidade(resultSet.getString("nome"));
                temperatura = new Temperatura(resultSet.getFloat("grau"));
                temperaturas = new ArrayList<>();
                
                Timestamp dataMedicao = resultSet.getTimestamp("data_hora_registro");
                temperatura.setDataMedicao(dataMedicao.toLocalDateTime());
                temperaturas.add(temperatura);
                
                cidade.setTemperaturas(temperaturas);
                
                listaCidade.add(cidade);
            
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCidade;
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

    @Override
    public Cidade getCidade(String nome) {
        Cidade cidade = null;
        String query = "SELECT nome, id FROM cidade WHERE nome = " + nome;
        try {
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(query);
            
            if(resultSet.next()){
                cidade = new Cidade(resultSet.getString("nome"));
                cidade.setId(resultSet.getInt("id"));
            } else {
                cidade = null;
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cidade;
    }

}
