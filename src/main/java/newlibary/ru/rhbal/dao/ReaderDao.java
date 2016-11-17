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
public class ReaderDao extends AbstractDao<Reader>{
    
    private static final String COLUMN_ID="id";
    private static final String TABLE_NAME="reader";
    private static final String COLUMN_LAST_NAME="lastName";
    private static final String COLUMN_FIRST_NAME="firstName";
    private static final String COLUMN_SURNAME="surname";
    private static final String COLUMN_AGE="age";
    
    
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getCreateSqlQuery(Reader entity) {
        return "INSERT INTO "+TABLE_NAME+" ("+COLUMN_FIRST_NAME+", "+COLUMN_LAST_NAME+", "+COLUMN_SURNAME+", "+COLUMN_AGE+") VALUES('"+
                entity.getFirstName()+"','"+entity.getLastName()+"','"+entity.getSurname()+"',"+entity.getAge()+")";
    }

    @Override
    public String getUpdateSqlQuery(Reader entity) {
        return "UPDATE "+TABLE_NAME+" SET "+COLUMN_FIRST_NAME+"= '"+entity.getFirstName()+"', "+COLUMN_SURNAME+"= '"+entity.getSurname()+
                "', "+COLUMN_LAST_NAME+"= '"+entity.getLastName()+"', "+COLUMN_AGE+"="+entity.getAge()+" WHERE id="+entity.getId();
    }

    @Override
    protected Reader toCollectEntity(ResultSet rs) throws SQLException {
        Reader reader = new Reader();
        reader.setId(rs.getInt(COLUMN_ID));
        reader.setFirstName(rs.getString(COLUMN_FIRST_NAME));
        reader.setLastName(rs.getString(COLUMN_LAST_NAME));
        reader.setSurname(rs.getString(COLUMN_SURNAME));
        reader.setAge(rs.getInt(COLUMN_AGE));
        return reader;     
    }
    
}
