/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Murilo Schrickte
 */

//Classe de conexão com o banco
public class DBConn {
    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USER = "root";
    private static final String PASSWORD = "masterkey";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}