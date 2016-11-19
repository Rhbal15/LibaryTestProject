/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Author;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.manager.AuthorController;
import newlibary.ru.rhbal.manager.ReaderController;
import newlibary.ru.rhbal.manager.BookController;
import newlibary.ru.rhbal.manager.BookStatusController;

/**
 *
 * @author User
 */

//Фасад для работы с интерфейсом консоли

public class Facade {
    private ReaderController ac;
    private BookController bc;
    private BookStatusController bsc;
    private AuthorController authc;

    public Facade() {
        ac=new ReaderController();
        bc=new BookController();
        bsc = new BookStatusController();
        authc=new AuthorController();
    }
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с книгами
    //-------------------------------------------------------------------------------------------------
    public void addBook(String name, int release, String genreName, int authorId) throws SQLException{
        bc.addBook(name, release, genreName, authorId);
    }
    
    public void deleteBook(int number) throws SQLException{
        bc.deleteBook(number);
    }
    
    public void editBook(int number, String newName,int release,String genreName, int authorId) throws SQLException{
        bc.editBook(number, newName, release, genreName, authorId);
    }
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с аккаунтами читателей
    //-------------------------------------------------------------------------------------------------
    
    //Создание читателя с доступом к веб-интерейсу
    public void createAccount(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        ac.create(login, password, lastName, firstName, surname, age);
    }
    
    //Создание читателя без доступа к веб-интерфейсу
   public void createAccount(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        ac.create(lastName, firstName, surname, age);
    }
    
    public void editAccount(int number,String newLastName,String newFirstName, String newSurname, int newAge) throws SQLException{
        ac.editReader(number, newLastName, newFirstName, newSurname, newAge);
    }
    
    public void deleteAccount(int id) throws SQLException{
        ac.delete(id);
    }
    
    public ArrayList<Reader> getAllReader() throws SQLException{
        return ac.getAll();
    }
    
    public void login(String login, String password) throws UserNotFoundException, NotCorrectPasswordException, SQLException{
        ac.login(login, password);
    }
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с учетными записями, сделанными по книгам
    //-------------------------------------------------------------------------------------------------
    
    public void getBook(int numberReader, int numberBook, GregorianCalendar mustBeReturned) throws NoBookInLibaryException, SQLException{
        bsc.getBook(numberBook,numberReader, mustBeReturned);
    }
    
    public void putBook(int numberBook) throws BookInTakenNotFoundException, SQLException{
        bsc.putBook(numberBook);
    }
    
    public ArrayList<Book> getAllBook() throws SQLException{
        return bsc.getBookInStock();
    }
    
    public ArrayList<BookStatus> getBookStatuses(int numberReader) throws SQLException{
        return bsc.getBookStatuses(numberReader);
    }
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с авторами 
    //-------------------------------------------------------------------------------------------------
    public void addAuthor(String name,int age) throws SQLException{
        authc.addAuthor(name, age);
    }
    
    public boolean deleteAuthor(int id) throws SQLException{
        return authc.deleteAuthor(id);
    }
    
    public void editAuthor(int id, String newName,int newAge) throws SQLException{
        authc.editAuthor(id, newName, newAge);
    }
    
    public ArrayList<Author> getAllAuthors() throws SQLException{
        return authc.getAll();
    }
    
}
