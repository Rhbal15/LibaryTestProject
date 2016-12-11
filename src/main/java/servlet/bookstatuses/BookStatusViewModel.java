/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.bookstatuses;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Reader;

/**
 *
 * @author User
 */
public class BookStatusViewModel {
    private int id;
    private Reader reader;
    private Book book;
    private String timeReciept;
    private String timeReturn;
    private String mustBeReturned;

    public BookStatusViewModel(int id, Reader reader, Book book, String timeReciept, String timeReturn, String mustBeReturned) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.timeReciept = timeReciept;
        this.timeReturn = timeReturn;
        this.mustBeReturned = mustBeReturned;
    }

    public BookStatusViewModel() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTimeReciept() {
        return timeReciept;
    }

    public void setTimeReciept(String timeReciept) {
        this.timeReciept = timeReciept;
    }

    public String getTimeReturn() {
        return timeReturn;
    }

    public void setTimeReturn(String timeReturn) {
        this.timeReturn = timeReturn;
    }

    public String getMustBeReturned() {
        return mustBeReturned;
    }

    public void setMustBeReturned(String mustBeReturned) {
        this.mustBeReturned = mustBeReturned;
    }

    
    
    
    
}
