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
public class BookDao extends AbstractDao<Book>{
    
    private static final String TABLE_NAME="book";
    private static final String COLUMN_AUTHOR="idAuthor";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_RELEASE="release";
    private static final String COLUMN_STATUS="status";
    private static final String COLUMN_GENRE="genre";
    private static final String COLUMN_ID="id";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getCreateSqlQuery(Book entity) {
        return "INSERT INTO "+TABLE_NAME+" ("+TABLE_NAME+"."+COLUMN_RELEASE+", "+COLUMN_STATUS+", "+COLUMN_GENRE+", "+COLUMN_AUTHOR+", "
                +COLUMN_NAME+") "+ "VALUES("+entity.getRelease()+","+entity.getStatus().ordinal()+",'"+entity.getGenre()+"',"+
                entity.getAuthor().getId()+",'"+entity.getName()+"')";
    }

    @Override
    public String getUpdateSqlQuery(Book entity) {
        return "UPDATE "+TABLE_NAME+" SET "+COLUMN_NAME+"= '"+entity.getName()+"', "+COLUMN_AUTHOR+"="+entity.getAuthor().getId()+", "+
                COLUMN_GENRE+"='"+entity.getGenre()+"', "+TABLE_NAME+"."+COLUMN_RELEASE+"= "+entity.getRelease()+", "+COLUMN_STATUS+
                "= "+entity.getStatus().ordinal()+ " WHERE id="+entity.getId();
    }

    @Override
    protected Book toCollectEntity(ResultSet rs) throws SQLException {
        Book book = new Book();
        AuthorDao authorDao=new AuthorDao();
        book.setId(rs.getInt(COLUMN_ID));
        book.setAuthor(authorDao.getById(rs.getInt(COLUMN_AUTHOR)));
        book.setGenre(rs.getString(COLUMN_GENRE));
        book.setName(rs.getString(COLUMN_NAME));
        book.setRelease(rs.getInt(COLUMN_RELEASE));
        book.setStatus(Status.values()[rs.getInt(COLUMN_STATUS)]);
        return book;
    }
    
}





