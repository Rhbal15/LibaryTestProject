/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;

import java.util.GregorianCalendar;



//Сущность учета данных о взятой читателем книге
public class BookStatus {
    private Book book;
    private GregorianCalendar timeReceipt;
    private GregorianCalendar timeReturn;
    private GregorianCalendar mustBeReturned;

    public BookStatus(Book book, GregorianCalendar mustBeReturned) {
        this.book = book;
        this.mustBeReturned = mustBeReturned;
        this.timeReceipt=new GregorianCalendar();
        book.setStatus(Status.TAKEN);
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

    public GregorianCalendar getMustReturned() {
        return mustBeReturned;
    }

    public void setMustReturned(GregorianCalendar mustReturned) {
        this.mustBeReturned = mustReturned;
    }

    
}
