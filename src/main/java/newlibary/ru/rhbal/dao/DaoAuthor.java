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
public class DaoAuthor extends AbstractDao<Author>{


    @Override
    public String getCreateSqlQuery(Author entity) {
        return "INSERT INTO "+getTableName()+" ("+getColumnName()+", "+getColumnAge()+") VALUES('"+entity.getName()+"',"+entity.getAge()+")";
    }

    @Override
    protected String getTableName() {
        return "author";
    }

    @Override
    public String getUpdateSqlQuery(Author entity) {
        return "UPDATE "+getTableName()+" SET "+getColumnName()+"= '"+entity.getName()+"', "+getColumnAge()+"="+entity.getAge()+" WHERE id="+entity.getId();
    }

    @Override
    protected Author toCollectEntity(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt(getColumnId()));
        author.setName(rs.getString(getColumnName()));
        author.setAge(rs.getInt(getColumnAge()));
        return author;
    }

    @Override
    protected String getColumnId() {
        return "id";
    }

    protected String getColumnName() {
        return "name";
    }

    protected String getColumnAge() {
        return "age";
    }

}
