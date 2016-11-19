/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Status;

/**
 *
 * @author User
 */
public class DaoBook extends AbstractDao<Book>{
  

    @Override
    protected String getTableName() {
        return "book";
    }

    @Override
    public String getCreateSqlQuery(Book entity) {
        return "INSERT INTO "+getTableName()+" ("+getTableName()+"."+getColumnRelease()+", "+getColumnStatus()+", "+getColumnGenre()+", "+getColumnAuthor()+", "
                +getColumnName()+") "+ "VALUES("+entity.getRelease()+","+entity.getStatus().ordinal()+",'"+entity.getGenre()+"',"+
                entity.getAuthor().getId()+",'"+entity.getName()+"')";
    }

    @Override
    public String getUpdateSqlQuery(Book entity) {
        return "UPDATE "+getTableName()+" SET "+getColumnName()+"= '"+entity.getName()+"', "+getColumnAuthor()+"="+entity.getAuthor().getId()+", "+
                getColumnGenre()+"='"+entity.getGenre()+"', "+getTableName()+"."+getColumnRelease()+"= "+entity.getRelease()+", "+getColumnStatus()+
                "= "+entity.getStatus().ordinal()+ " WHERE id="+entity.getId();
    }

    @Override
    protected Book toCollectEntity(ResultSet rs) throws SQLException {
        Book book = new Book();
        DaoAuthor authorDao=new DaoAuthor();
        book.setId(rs.getInt(getColumnId()));
        book.setAuthor(authorDao.getById(rs.getInt(getColumnAuthor())));
        book.setGenre(rs.getString(getColumnGenre()));
        book.setName(rs.getString(getColumnName()));
        book.setRelease(rs.getInt(getColumnRelease()));
        book.setStatus(Status.values()[rs.getInt(getColumnStatus())]);
        return book;
    }

    @Override
    protected String getColumnId() {
        return "id";
    }
    
    protected String getColumnAuthor() {
        return "idAuthor";
    }
    
    protected String getColumnName() {
        return "name";
    }
    
    protected String getColumnRelease() {
        return "release";
    }
    
    protected String getColumnStatus() {
        return "status";
    }
    
    protected String getColumnGenre() {
        return "genre";
    }
    
}





