/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newlibary.ru.rhbal.libaryproject.NewLibary;

/**
 *
 * @author User
 */
public class Connector {
    private Connection dbConnection;
    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
    private static final String DATA_BASE_URL ="jdbc:mysql://localhost:3306/libary";
    private static final String DATA_BASE_USER_NAME="root";
    private static final String DATA_BASE_PASSWORD="root";
    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException ex) {
            System.err.println("Не удалось найти дирайвер");
        }
        try {
            dbConnection=DriverManager.getConnection(DATA_BASE_URL,DATA_BASE_USER_NAME,DATA_BASE_PASSWORD);
        } catch (SQLException ex) {
            System.err.println("Не удалось подключится к базе данных");
        }
        return dbConnection;
    }
    
    public void close(){
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            System.err.println("Не удалось закрыть соединение");
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
