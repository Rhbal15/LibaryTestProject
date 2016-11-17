/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Entity;

/**
 *
 * @author User
 */
public abstract class AbstractDao <E extends Entity> {
    
    public void create(E entity)throws SQLException{
        Connector connector=new Connector();
        Connection connection=connector.getConnection();
        System.out.println(getCreateSqlQuery(entity));
        connection.createStatement().executeUpdate(getCreateSqlQuery(entity));
        connector.close();
    };    
    
    public E getById(int id) throws SQLException{
        Connector connector=new Connector();
        Connection connection=connector.getConnection();
        String sqlQuery="SELECT * FROM "+getTableName()+" WHERE id="+id;
        ResultSet rs= connection.createStatement().executeQuery(sqlQuery);
        if(!rs.last())
            return null;
        rs.first();
        E entity =toCollectEntity(rs);
        connector.close();
        return entity;
    };
    
    public boolean update(E entity)throws SQLException{
        Connector connector=new Connector();
        Connection connection=connector.getConnection();
        System.out.println(getUpdateSqlQuery(entity));
        connection.createStatement().execute(getUpdateSqlQuery(entity));
        connection.close();
        return true;
    };
    
    public boolean delete(E entity)throws SQLException{
        Connector connector=new Connector();
        Connection connection=connector.getConnection();
        String sqlQuery="DELETE FROM "+getTableName()+" WHERE id="+entity.getId();
        connection.createStatement().execute(sqlQuery);
        return true;
    };
    
    public ArrayList<E> getAll() throws SQLException{
        Connector connector=new Connector();
        Connection connection=connector.getConnection();
        String sqlQuery="SELECT * FROM "+getTableName();
        ResultSet rs= connection.createStatement().executeQuery(sqlQuery);
        ArrayList<E> entities = new ArrayList<>();
        while(rs.next()){
            entities.add(toCollectEntity(rs));
        }
        connector.close();
        return entities;
    };
    
    public abstract String getTableName();
    public abstract String getCreateSqlQuery(E entity);
    public abstract String getUpdateSqlQuery(E entity);
    protected abstract E toCollectEntity(ResultSet rs) throws SQLException;
    
}