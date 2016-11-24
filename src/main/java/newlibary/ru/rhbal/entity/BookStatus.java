/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;

import java.util.GregorianCalendar;



//Сущность учета данных о взятой читателем книге
public class BookStatus extends Entity<Integer>{
    private int id;
    private Book book;
    private GregorianCalendar timeReceipt;
    private GregorianCalendar timeReturn;
    private GregorianCalendar mustBeReturned;
    private Reader reader;

    public BookStatus(Book book,Reader reader, GregorianCalendar mustBeReturned) {
        this.book = book;
        this.reader=reader;
        this.mustBeReturned = mustBeReturned;
        this.timeReceipt=new GregorianCalendar();
    }

    public BookStatus() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public GregorianCalendar getTimeReceipt() {
        return timeReceipt;
    }

    public void setTimeReceipt(GregorianCalendar timeReceipt) {
        this.timeReceipt = timeReceipt;
    }

    public GregorianCalendar getTimeReturn() {
        return timeReturn;
    }

    public void setTimeReturn(GregorianCalendar timeReturn) {
        this.timeReturn = timeReturn;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public GregorianCalendar getMustBeReturned() {
        return mustBeReturned;
    }

    public void setMustBeReturned(GregorianCalendar mustBeReturned) {
        this.mustBeReturned = mustBeReturned;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }


}
