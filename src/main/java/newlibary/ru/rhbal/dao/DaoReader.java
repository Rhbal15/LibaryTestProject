/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Reader;

/**
 *
 * @author User
 */
public class DaoReader extends AbstractDao<Reader>{
    
    
    @Override
    protected String getTableName() {
        return "reader";
    }

    @Override
    public String getCreateSqlQuery(Reader entity) {
        if(entity.getLogin()==null || entity.getPassword()==null)
            return "INSERT INTO "+getTableName()+" ("+getColumnFirstName()+", "+getColumnLastName()+", "+getColumnSurname()+", "+getColumnAge()+") VALUES('"+
                entity.getFirstName()+"','"+entity.getLastName()+"','"+entity.getSurname()+"',"+entity.getAge()+")";
        
       return "INSERT INTO "+getTableName()+" ("+getColumnLogin()+", "+getColumnPassword()+", "+getColumnFirstName()+", "+getColumnLastName()+", "+
                getColumnSurname()+", "+getColumnAge()+") VALUES('"+entity.getLogin()+"','"+entity.getPassword()+"','"+entity.getFirstName()+"','"+entity.getLastName()+"','"+entity.getSurname()+
                "',"+entity.getAge()+")";
    }

    @Override
    public String getUpdateSqlQuery(Reader entity) {
        return "UPDATE "+getTableName()+" SET "+getColumnLogin()+"= '"+entity.getLogin()+"', '"+getColumnPassword()+"= '"+entity.getPassword()+
                "', '"+getColumnFirstName()+"= '"+entity.getFirstName()+"', "+getColumnSurname()+"= '"+entity.getSurname()+"', "
                +getColumnLastName()+"= '"+entity.getLastName()+"', "+getColumnAge()+"="+entity.getAge()+" WHERE id="+entity.getId();
    }

    @Override
    protected Reader toCollectEntity(ResultSet rs) throws SQLException {
        Reader reader = new Reader();
        reader.setId(rs.getInt(getColumnId()));
        reader.setFirstName(rs.getString(getColumnFirstName()));
        reader.setLastName(rs.getString(getColumnLastName()));
        reader.setSurname(rs.getString(getColumnSurname()));
        reader.setAge(rs.getInt(getColumnAge()));
        reader.setLogin(rs.getString(getColumnLogin()));
        reader.setPassword(rs.getString(getColumnPassword()));
        return reader;     
    }

    @Override
    protected String getColumnId() {
        return "id";
    }
    
    protected String getColumnLogin() {
        return "login";
    }
    
    protected String getColumnPassword() {
        return "password";
    }
    
    protected String getColumnLastName() {
        return "lastName";
    }
    
    protected String getColumnFirstName() {
        return "firstName";
    }
    
    protected String getColumnSurname() {
        return "surname";
    }
    
    protected String getColumnAge() {
        return "age";
    }
}
