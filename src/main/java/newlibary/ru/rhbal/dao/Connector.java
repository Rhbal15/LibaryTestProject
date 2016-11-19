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
    
    public Connection getConnection(){
        
        //��������� �������
        try {
            Class.forName(getDriverClassString());
        } catch (ClassNotFoundException ex) {
            System.err.println("�� ������� ����� ��������");
        }
        
        //�������� ���������� � ��
        try {
            dbConnection=DriverManager.getConnection(getDataBaseURL(),getDataBaseUserName(),getDataBasePassword());
        } catch (SQLException ex) {
            System.err.println("�� ������� ����������� � ���� ������");
        }
        return dbConnection;
    }
    
    public void close(){
        //��������� ���������� � ��
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            System.err.println("�� ������� ������� ����������");
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected String getDriverClassString(){
        return "com.mysql.jdbc.Driver";
    }
    
    protected String getDataBaseURL(){
        return "jdbc:mysql://localhost:3306/libary";
    }
    
    protected String getDataBaseUserName(){
        return "root";
    }
    
    protected String getDataBasePassword(){
        return "root";
    }
    
    
}
