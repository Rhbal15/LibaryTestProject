/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;

/**
 *
 * @author User
 */
public class BookStatusDao extends AbstractDao<BookStatus>{

    private static final String TABLE_NAME="bookstatus";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_TIME_RECEIPT="timeReceipt";
    private static final String COLUMN_TIME_RETURN="timeReturn";
    private static final String COLUMN_MUST_BE_RETURNED="mustBeReturned";
    private static final String COLUMN_ID_BOOK="idBook";
    private static final String COLUMN_ID_READER="idReader";
    
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getCreateSqlQuery(BookStatus entity) {
        if(entity.getTimeReturn()!=null)
            return "INSERT INTO "+TABLE_NAME+" ("+COLUMN_TIME_RECEIPT+", "+COLUMN_TIME_RETURN+", "+COLUMN_MUST_BE_RETURNED+", "+
                COLUMN_ID_BOOK+", "+COLUMN_ID_READER+") VALUES("+gregorianCalendarToString(entity.getTimeReceipt())+","+
                gregorianCalendarToString(entity.getTimeReturn())+","+gregorianCalendarToString(entity.getMustBeReturned())+","+
                entity.getBook().getId()+","+entity.getReader().getId()+")";
        
        return "INSERT INTO "+TABLE_NAME+" ("+COLUMN_TIME_RECEIPT+", "+COLUMN_MUST_BE_RETURNED+", "+
                COLUMN_ID_BOOK+", "+COLUMN_ID_READER+") VALUES("+gregorianCalendarToString(entity.getTimeReceipt())+","+
                gregorianCalendarToString(entity.getMustBeReturned())+","+entity.getBook().getId()+","+entity.getReader().getId()+")";
    }

    @Override
    public String getUpdateSqlQuery(BookStatus entity) {
        if(entity.getTimeReturn()!=null)
            return "UPDATE "+TABLE_NAME+" SET "+COLUMN_TIME_RECEIPT+"= "+gregorianCalendarToString(entity.getTimeReceipt())+
                ", "+COLUMN_TIME_RETURN+"="+gregorianCalendarToString(entity.getTimeReturn())+", "+COLUMN_TIME_RETURN+"="+
                gregorianCalendarToString(entity.getMustBeReturned())+" WHERE id="+entity.getId();
        
        return "UPDATE "+TABLE_NAME+" SET "+COLUMN_TIME_RECEIPT+"= "+gregorianCalendarToString(entity.getTimeReceipt())+
                ", "+COLUMN_TIME_RETURN+"="+gregorianCalendarToString(entity.getMustBeReturned())+" WHERE id="+entity.getId();
    }
    
    private String gregorianCalendarToString(GregorianCalendar gc){
        return "'"+gc.get(Calendar.YEAR)+"-"+gc.get(Calendar.MONTH)+"-"+gc.get(Calendar.DAY_OF_MONTH)+"'";
    }

    @Override
    protected BookStatus toCollectEntity(ResultSet rs) throws SQLException {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setId(rs.getInt(COLUMN_ID));
        bookStatus.setBook(new BookDao().getById(rs.getInt(COLUMN_ID_BOOK)));
        bookStatus.setReader(new ReaderDao().getById(rs.getInt(COLUMN_ID_READER)));
        Date timeReceipt = rs.getDate(COLUMN_TIME_RECEIPT);
        bookStatus.setTimeReceipt(new GregorianCalendar(timeReceipt.getYear(), timeReceipt.getMonth(), timeReceipt.getDate()));
        Date timeReturn = rs.getDate(COLUMN_TIME_RETURN);
        if(timeReturn!=null)
            bookStatus.setTimeReturn(new GregorianCalendar(timeReturn.getYear(), timeReturn.getMonth(), timeReturn.getDate()));
        Date mustBeReturned = rs.getDate(COLUMN_MUST_BE_RETURNED);
        bookStatus.setMustBeReturned(new GregorianCalendar(mustBeReturned.getYear(), mustBeReturned.getMonth(), mustBeReturned.getDate()));
        return bookStatus;
    }
    
}
