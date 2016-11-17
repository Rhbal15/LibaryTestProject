/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.facade;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Account;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Genre;
import newlibary.ru.rhbal.manager.AccountController;
import newlibary.ru.rhbal.manager.BookController;
import newlibary.ru.rhbal.manager.BookStatusController;
import newlibary.ru.rhbal.manager.GenreController;

/**
 *
 * @author User
 */

//Фасад для работы с интерфейсом консоли

public class Facade {
    private AccountController ac;
    private BookController bc;
    private BookStatusController bsc;
    private GenreController gc;

    public Facade() {
        ac=new AccountController();
        bc=new BookController();
        bsc = new BookStatusController();
        gc = new GenreController();
    }
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с книгами
    //-------------------------------------------------------------------------------------------------
    public void addBook(String name, int release, String genreName, String authorName,int authorAge){
        bc.addBook(name, release, genreName, authorName, authorAge);
    }
    
    public void deleteBook(int number){
        bc.deleteBook(number);
    }
    
    public void editBook(int number, String newName,int release,String genreName, String authorName, int authorYear){
        bc.editBook(number, newName, release, genreName, authorName, authorYear);
    }
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с жанрами
    //-------------------------------------------------------------------------------------------------
    public void addGenre(String name){
        gc.addGenre(name);
    }
    
    public void deleteGenre(int id){
        gc.deleteGenre(id);
    }
    
    public void editGenre(int id, String newName){
        gc.editGenre(id, newName);
    }
    
    public ArrayList<Genre> getAllGenre(){
        return gc.getAllGenre();
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с аккаунтами читателей
    //-------------------------------------------------------------------------------------------------
    
    //Создание читателя с доступом к веб-интерейсу
    public void createAccount(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        ac.create(login, password, lastName, firstName, surname, age);
    }
    
    //Создание читателя без доступа к веб-интерфейсу
    public void createAccount(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        ac.create(lastName, firstName, surname, age);
    }
    
    public void editAccount(int number,String newLastName,String newFirstName, String newSurname, int newAge){
        ac.editReader(number, newLastName, newFirstName, newSurname, newAge);
    }
    
    public void deleteAccount(int id){
        ac.delete(id);
    }
    
    public ArrayList<Account> getAllAccounts(){
        return ac.getAll();
    }
    
    public void login(String login, String password) throws UserNotFoundException, NotCorrectPasswordException{
        ac.login(login, password);
    }
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Работа с учетными записями, сделанными по книгам
    //-------------------------------------------------------------------------------------------------
    
    public void getBook(int numberReader, int numberBook, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        bsc.getBook(numberReader,numberBook, mustBeReturned);
    }
    
    public void putBook(int numberReader, int numberBook) throws BookInTakenNotFoundException{
        bsc.putBook(numberReader,numberBook);
    }
    
    public ArrayList<Book> getAllBook(){
        return bsc.getBookInStock();
    }
    
    public ArrayList<BookStatus> getBookStatuses(int numberReader){
        return bsc.getBookStatuses(numberReader);
    }
    //-------------------------------------------------------------------------------------------------
    
}
