/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.DaoBook;
import newlibary.ru.rhbal.dao.DaoBookStatus;
import newlibary.ru.rhbal.dao.DaoReader;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Status;

/**
 *
 * @author User
 */
public class BookStatusController {

    DaoBookStatus daoBookStatus;
    DaoBook daoBook;

    public BookStatusController() {
        daoBookStatus = new DaoBookStatus();
        daoBook = new DaoBook();
    }

    //-------------------------------------------------------------------------------------------------
    //������ ��������� ���� ���������
    //-------------------------------------------------------------------------------------------------
    public void getBook(int numberBook, int numberReader, GregorianCalendar mustBeReturned) throws NoBookInLibaryException, SQLException {
        BookStatus bookStatus= new BookStatus(new DaoBook().getById(numberBook), new DaoReader().getById(numberReader), mustBeReturned);
        daoBookStatus.create(bookStatus);
    }

    //-------------------------------------------------------------------------------------------------
    public void putBook(int numberBook) throws BookInTakenNotFoundException, SQLException {
        BookStatus bookStatus=null;
        
        for(BookStatus value: daoBookStatus.getAll()){
            if(value.getBook().getId().equals(numberBook)){
                bookStatus=value;
                break;
            }
        }
        
        if(bookStatus==null || bookStatus.getBook().getStatus()==Status.INSTOCK)
            throw new BookInTakenNotFoundException();
        
        bookStatus.getBook().setStatus(Status.INSTOCK);
        bookStatus.setTimeReturn(new GregorianCalendar());
    }

    //����� ���������� ��� ����� ������ ���������������� � ��������� �������������
    public ArrayList<Book> getBookTaken() throws SQLException {

        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<Book> books = new ArrayList<>();

        //���������� ��� ������� ������ ������������, ����������, �� ��� �� ����� 
        for (BookStatus value : daoBookStatus.getAll()) {
            if (value.getBook().getStatus() == Status.TAKEN) {
                books.add(value.getBook());
            }
        }
        return books;
    }

    public ArrayList<BookStatus> getBookStatuses(int numberReader) throws SQLException {
        ArrayList<BookStatus> bookStatuses=new ArrayList<>();
        for(BookStatus value: daoBookStatus.getAll()){
            if(value.getReader().getId().equals(numberReader))
                bookStatuses.add(value);
        }
        return bookStatuses;
    }

    //����� ���������� ��� �����, ������� ��������� � ����������
    public ArrayList<Book> getBookInStock() throws SQLException {
        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<Book> books = new ArrayList<>();

        //���������� ��� �����, ����������, �� ��� � ����������
        for (Book value : daoBook.getAll()) {
            if (value.getStatus() == Status.INSTOCK) {
                books.add(value);
            }
        }
        return books;
    }

    //����� ���������� ����� ������ ����� ��������� ����
    public ArrayList<BookStatus> booksTakenOverTime(GregorianCalendar calendar) throws SQLException {
        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        DaoReader daoReader=new DaoReader();
        //���������� ��� ��������, � � ��� ��� ������� ������, ����������, �� ��� ����� �������� ����
        for (Reader value : daoReader.getAll()) {
            for (BookStatus bookStatus : value.getBookStatuses()) {
                if (bookStatus.getTimeReceipt().compareTo(calendar) >= 0) {
                    bookStatuses.add(bookStatus);
                }
            }
        }
        return bookStatuses;
    }

    //����� ���������� ����� ������ �����-���� ���������� �������������
    public ArrayList<BookStatus> booksIndividualReader(Reader reader) throws SQLException {
        
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        for (BookStatus bookStatus : daoBookStatus.getAll()) {
            if(bookStatus.getReader().getId().equals(reader.getId()))
                    bookStatuses.add(bookStatus);
        }
        return bookStatuses;
    }
}
