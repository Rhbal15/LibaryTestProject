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
public class DaoBookStatus extends AbstractDao<BookStatus> {

    @Override
    protected String getTableName() {
        return "bookstatus";
    }

    @Override
    public String getCreateSqlQuery(BookStatus entity) {
        if (entity.getTimeReturn() != null) {
            return "INSERT INTO " + getTableName() + " (" + getColumnTimeReceipt() + ", " + getColumnTimeReturn() + ", " + getColumnMustBeReturned() + ", "
                    + getColumnBook() + ", " + getColumnReader() + ") VALUES(" + gregorianCalendarToString(entity.getTimeReceipt()) + ","
                    + gregorianCalendarToString(entity.getTimeReturn()) + "," + gregorianCalendarToString(entity.getMustBeReturned()) + ","
                    + entity.getBook().getId() + "," + entity.getReader().getId() + ")";
        }

        return "INSERT INTO " + getTableName() + " (" + getColumnTimeReceipt() + ", " + getColumnMustBeReturned() + ", "
                + getColumnBook() + ", " + getColumnReader() + ") VALUES(" + gregorianCalendarToString(entity.getTimeReceipt()) + ","
                + gregorianCalendarToString(entity.getMustBeReturned()) + "," + entity.getBook().getId() + "," + entity.getReader().getId() + ")";
    }

    @Override
    public String getUpdateSqlQuery(BookStatus entity) {
        if (entity.getTimeReturn() != null) {
            return "UPDATE " + getTableName() + " SET " + getColumnTimeReceipt() + "= " + gregorianCalendarToString(entity.getTimeReceipt())
                    + ", " + getColumnTimeReturn() + "=" + gregorianCalendarToString(entity.getTimeReturn()) + ", " + getColumnMustBeReturned() + "="
                    + gregorianCalendarToString(entity.getMustBeReturned()) + " WHERE id=" + entity.getId();
        }

        return "UPDATE " + getTableName() + " SET " + getColumnTimeReceipt() + "= " + gregorianCalendarToString(entity.getTimeReceipt())
                + ", " + getColumnTimeReturn() + "=" + gregorianCalendarToString(entity.getMustBeReturned()) + " WHERE id=" + entity.getId();
    }

    private String gregorianCalendarToString(GregorianCalendar gc) {
        return "'" + gc.get(Calendar.YEAR) + "-" + gc.get(Calendar.MONTH) + "-" + gc.get(Calendar.DAY_OF_MONTH) + "'";
    }

    @Override
    protected BookStatus toCollectEntity(ResultSet rs) throws SQLException {

        BookStatus bookStatus = new BookStatus();

        bookStatus.setId(rs.getInt(getColumnId()));
        bookStatus.setBook(new DaoBook().getById(rs.getInt(getColumnBook())));
        bookStatus.setReader(new DaoReader().getById(rs.getInt(getColumnReader())));
        Date timeReceipt = rs.getDate(getColumnTimeReceipt());

        bookStatus.setTimeReceipt(dateToGreagorianCalendarConverter(timeReceipt));
        Date timeReturn = rs.getDate(getColumnTimeReturn());

        if (timeReturn != null) {
            bookStatus.setTimeReturn(dateToGreagorianCalendarConverter(timeReturn));
        }

        Date mustBeReturned = rs.getDate(getColumnMustBeReturned());

        bookStatus.setMustBeReturned(dateToGreagorianCalendarConverter(mustBeReturned));
        return bookStatus;
    }

    @Override
    protected String getColumnId() {
        return "id";
    }

    protected String getColumnTimeReceipt() {
        return "timeReceipt";
    }

    protected String getColumnTimeReturn() {
        return "timeReturn";
    }

    protected String getColumnMustBeReturned() {
        return "mustBeReturned";
    }

    protected String getColumnBook() {
        return "idBook";
    }

    protected String getColumnReader() {
        return "idReader";
    }

    private GregorianCalendar dateToGreagorianCalendarConverter(Date date) {
        return new GregorianCalendar(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
    }

}
