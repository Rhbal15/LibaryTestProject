/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.DaoBook;
import newlibary.ru.rhbal.dao.DaoBookStatus;
import newlibary.ru.rhbal.dao.DaoReader;
import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Status;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 *
 * @author User
 */
public class BookStatusController {

    DaoBookStatus daoBookStatus;
    DaoBook daoBook;
    ArrayList<BookStatus> bookStatuses;
    boolean isChanged = true;

    public BookStatusController() {
        daoBookStatus = new DaoBookStatus();
        daoBook = new DaoBook();
        bookStatuses=new ArrayList<>();

    }

    //-------------------------------------------------------------------------------------------------
    //Методы получения книг читателем
    //-------------------------------------------------------------------------------------------------
    public int getBook(int numberBook, int numberReader, GregorianCalendar mustBeReturned) throws SQLException, EntityNotFoundException {

        Book book=new DaoBook().getById(numberBook);

        if(book==null || book.getStatus()==Status.TAKEN){
            throw new EntityNotFoundException("Добавить учетную запись не удалось, книга не найдена");
        }

        Reader reader = new DaoReader().getById(numberReader);
        if(reader==null){
            throw new EntityNotFoundException("Добавить учетную запись не удалось, читатель не найден");
        }

        BookStatus bookStatus = new BookStatus(book,reader , mustBeReturned);
        book.setStatus(Status.TAKEN);
        daoBook.update(book);
        int id=daoBookStatus.create(bookStatus);

        isChanged = true;

        return id;
    }

    //-------------------------------------------------------------------------------------------------
    public void putBook(int numberBookStatus) throws BookInTakenNotFoundException, SQLException {
        BookStatus bookStatus = daoBookStatus.getById(numberBookStatus);

        if (bookStatus == null || bookStatus.getBook().getStatus() == Status.INSTOCK) {
            throw new BookInTakenNotFoundException("Книга в библиотеке не найдена");
        }

        bookStatus.getBook().setStatus(Status.INSTOCK);
        bookStatus.setTimeReturn(new GregorianCalendar());

        daoBookStatus.update(bookStatus);
        daoBook.update(bookStatus.getBook());

        isChanged = true;
    }

    public void putBookRemove(int numberBookStatus) throws SQLException, BookInTakenNotFoundException {
        BookStatus bookStatus = daoBookStatus.getById(numberBookStatus);

        if (bookStatus == null || bookStatus.getBook().getStatus() == Status.TAKEN) {
            throw new BookInTakenNotFoundException("Отменить действие не удалось");
        }

        bookStatus.getBook().setStatus(Status.TAKEN);
        bookStatus.setTimeReturn(null);

        daoBookStatus.update(bookStatus);
        daoBook.update(bookStatus.getBook());

        isChanged = true;
    }

    public boolean deleteBookStatus(int id) throws SQLException, EntityNotFoundException {
        BookStatus bookStatus=daoBookStatus.getById(id);

        if(bookStatus==null){
            throw new EntityNotFoundException("Книга не найдена, удаление не произошло");
        }

        Book book=daoBook.getById(bookStatus.getBook().getId());
        book.setStatus(Status.INSTOCK);
        daoBook.update(book);
        return daoBookStatus.delete(bookStatus);
    }

    //Метод возвращает все книги взятые авторизированным в программе пользователем
    public ArrayList<Book> getBookTaken() throws SQLException {

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

    //Метод возвращающий все учетные записи пользователя
    public ArrayList<BookStatus> getBookStatuses(int numberReader) throws SQLException {

        bookStatuses=new ArrayList<>();
        for (BookStatus value : daoBookStatus.getAll()) {

            if (value.getReader().getId().equals(numberReader)) {
                bookStatuses.add(value);

            }
        }
        isChanged=false;
        return bookStatuses;
    }

    //Метод возвращает все книги, которые находятся в библиотеке
    public ArrayList<Book> getBookInStock() throws SQLException {
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
    public ArrayList<BookStatus> booksTakenOverTime(GregorianCalendar calendar) throws SQLException {
        //инициализируем список, в который будем записывать книги
        ArrayList<BookStatus> bookStatuses = new ArrayList<>();

        DaoReader daoReader = new DaoReader();
        //Перебираем все аккаунты, а в них все учетные записи, записываем, те что позже заданной даты
        for (Reader value : daoReader.getAll()) {
            for (BookStatus bookStatus : value.getBookStatuses()) {
                if (bookStatus.getTimeReceipt().compareTo(calendar) >= 0) {
                    bookStatuses.add(bookStatus);
                }
            }
        }
        return bookStatuses;
    }

    //Метод возвращает книги взятые когда-либо конкретным пользователем
    public ArrayList<BookStatus> booksIndividualReader(int readerNumber) throws SQLException {

        ArrayList<BookStatus> bookStatuses = new ArrayList<>();

        for (BookStatus bookStatus : daoBookStatus.getAll()) {
            if (bookStatus.getReader().getId().equals(readerNumber)) {
                bookStatuses.add(bookStatus);
            }
        }
        return bookStatuses;
    }
}
