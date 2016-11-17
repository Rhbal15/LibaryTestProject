/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import newlibary.ru.rhbal.entity.Author;

/**
 *
 * @author User
 */
public class AuthorDao extends AbstractDao<Author>{
    private static final String TABLE_NAME="author";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_AGE="age";
   
    @Override
    public String getCreateSqlQuery(Author entity) {
        return "INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+", "+COLUMN_AGE+") VALUES('"+entity.getName()+"',"+entity.getAge()+")";
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getUpdateSqlQuery(Author entity) {
        return "UPDATE "+TABLE_NAME+" SET "+COLUMN_NAME+"= '"+entity.getName()+"', "+COLUMN_AGE+"="+entity.getAge()+" WHERE id="+entity.getId();
    }

    @Override
    protected Author toCollectEntity(ResultSet rs) throws SQLException {
            Author author = new Author();
            author.setId(rs.getInt(COLUMN_ID));
            author.setName(rs.getString(COLUMN_NAME));
            author.setAge(rs.getInt(COLUMN_AGE));
            return author;
    }
    
}
