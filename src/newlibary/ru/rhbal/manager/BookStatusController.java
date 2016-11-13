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
    //Методы получения книг читателем
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

    //Метод возвращает все книги взятые авторизированным в программе пользователем
    public ArrayList<Book> getBookTaken() {

        //инициализируем список, в который будем записывать книги
        ArrayList<Book> books = new ArrayList<>();

        //Перебираем все учетные записи пользователя, записываем, те что на руках 
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

    //Метод возвращает все книги, которые находятся в библиотеке
    public ArrayList<Book> getBookInStock() {
        //инициализируем список, в который будем записывать книги
        ArrayList<Book> books = new ArrayList<>();

        //Перебираем все книги, записываем, те что в библиотеке
        for (Book value : daoBook.getAll()) {
            if (value.getStatus() == Status.INSTOCK) {
                books.add(value);
            }
        }
        return books;
    }

    //Метод возвращает книги взятые позже некоторой даты
    public ArrayList<BookStatus> booksTakenOverTime(GregorianCalendar calendar) {
        //инициализируем список, в который будем записывать книги
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        DaoAccount daoAccount=new DaoAccount();
        //Перебираем все аккаунты, а в них все учетные записи, записываем, те что позже заданной даты
        for (Account value : daoAccount.getAll()) {
            for (BookStatus bookStatus : value.getBookStatuses()) {
                if (bookStatus.getTimeReceipt().compareTo(calendar) >= 0) {
                    bookStatuses.add(bookStatus);
                }
            }
        }
        return bookStatuses;
    }

    //Метод возвращает книги взятые когда-либо конкретным пользователем
    public ArrayList<BookStatus> booksIndividualReader(Account account) {
        
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();
        
        for (BookStatus bookStatus : account.getBookStatuses()) {
            bookStatuses.add(bookStatus);
        }
        return bookStatuses;
    }
}
