/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.DaoAccount;
import newlibary.ru.rhbal.dao.DaoBook;
import newlibary.ru.rhbal.dao.DaoBookStatus;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.entity.Account;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Model;
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
    public void getBook(int numberReader, int numberBook, GregorianCalendar mustBeReturned) throws NoBookInLibaryException {
        daoBookStatus.create(numberReader, numberBook, mustBeReturned);
    }

    public void getBook(String name, GregorianCalendar mustBeReturned, Account account) throws NoBookInLibaryException {
        daoBookStatus.create(name, mustBeReturned);
    }

    //-------------------------------------------------------------------------------------------------
    public void putBook(int numberReader, int numberBook) throws BookInTakenNotFoundException {
        daoBookStatus.putBook(numberReader, numberBook);
    }

    //����� ���������� ��� ����� ������ ���������������� � ��������� �������������
    public ArrayList<Book> getBookTaken() {

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

    public ArrayList<BookStatus> getBookStatuses(int numberReader) {
        return Model.getINSTANCE().getAccounts().get(numberReader).getBookStatuses();
    }

    //����� ���������� ��� �����, ������� ��������� � ����������
    public ArrayList<Book> getBookInStock() {
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
    public ArrayList<BookStatus> booksTakenOverTime(GregorianCalendar calendar) {
        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        DaoAccount daoAccount=new DaoAccount();
        //���������� ��� ��������, � � ��� ��� ������� ������, ����������, �� ��� ����� �������� ����
        for (Account value : daoAccount.getAll()) {
            for (BookStatus bookStatus : value.getBookStatuses()) {
                if (bookStatus.getTimeReceipt().compareTo(calendar) >= 0) {
                    bookStatuses.add(bookStatus);
                }
            }
        }
        return bookStatuses;
    }

    //����� ���������� ����� ������ �����-���� ���������� �������������
    public ArrayList<BookStatus> booksIndividualReader(Account account) {
        
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        for (BookStatus bookStatus : account.getBookStatuses()) {
            bookStatuses.add(bookStatus);
        }
        return bookStatuses;
    }
}
