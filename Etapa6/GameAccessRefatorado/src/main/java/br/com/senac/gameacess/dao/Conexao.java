package br.com.senac.gameacess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/gameaccessdb"; 
    
    
    private static final String USER = "root";
    private static final String PASS = "senha123456Forte"; 

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}