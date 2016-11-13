/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.entity.Account;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Model;
import newlibary.ru.rhbal.entity.Status;
import newlibary.ru.rhbal.manager.UserInSystem;


//DAO для работы с учетными записями книг
public class DaoBookStatus extends AbstractDao<BookStatus>{
    
    
    //-------------------------------------------------------------------------------------------------
    //Методы создания учетных записей (Взятие книг из библиотеки)
    //-------------------------------------------------------------------------------------------------
    
    //Создание учетной записи книги по её названию, учетная запись прикрепляется к пользователю
    //авторизированному в системе
    public BookStatus create(String name, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //Находим книгу по имени, если её нет то выбрасываем исключение
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getBook(name); 
        if(book==null)
            throw new NoBookInLibaryException();
        
        //Создаем для книги учетную запись, заносим её в модель
        BookStatus bookStatus = new BookStatus(book, mustBeReturned);
        getModel().add(bookStatus);
        return bookStatus;
    }
    
    //Метод для создания учетной записи по номер книги и номеру читателя
    public BookStatus create(int numberReader, int numberBook, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //Находим книгу по номеру, если её нет, то выбрасываем исключение
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getAll().get(numberBook);
        if(book==null)
            throw new NoBookInLibaryException();
        
        //Создаем для книги учетную запись, заносим её в модель
        BookStatus bookStatus=new BookStatus(book,mustBeReturned);
        Model.getINSTANCE().getAccounts().get(numberReader).getBookStatuses().add(bookStatus);
        return bookStatus;
    }
    
    //Создание учетной записи по аккаунту и названию книги
    public BookStatus create(String name,Account account, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //Находим книгу по её названию
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getBook(name); 
        if(book==null)
            throw new NoBookInLibaryException();
        
        //Создаем для книги учетную запись, заносим её в модель
        //конкретным читателем
        BookStatus bookStatus = new BookStatus(book, mustBeReturned);
        account.getBookStatuses().add(bookStatus);
        return bookStatus;
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Методы возвращения книг в библиотеку
    //-------------------------------------------------------------------------------------------------
    
    //Возвращение книги по её имени, поиск учетных записей осуществляется у авторизированного пользователя
    public void putBook(String name) throws BookInTakenNotFoundException{
        
        //Ищем учетную запись, если нашли, то меняем её статус и время возврата, если нет 
        //выбрасываем исключение
        for(BookStatus value: getModel()){
            if(value.getBook().getName().equals(name)){
                closeStatus(value);
                return;
            }
            throw new BookInTakenNotFoundException();
        }
    }
    
    //Возвращение книги по её имени и аккаунту
    public void putBook(String name, Account account) throws BookInTakenNotFoundException{
        //Ищем учетную запись в конкретном аккаунте, если нашли, то меняем её статус и время возврата, если нет 
        //выбрасываем исключение
        for(BookStatus value: account.getBookStatuses()){
            if(value.getBook().getName().equals(name)){
                closeStatus(value);
                return;
            }
            throw new BookInTakenNotFoundException();
        }
    }
    
    //Возвращение книги по её номеру и номеру аккаунта
    public void putBook(int numberReader, int numberBook) throws BookInTakenNotFoundException{
        //Указываем на учетную запись
        BookStatus bookStatus = Model.getINSTANCE().getAccounts().get(numberReader).getBookStatuses().get(numberBook);
        
        //меняем её статус
        closeStatus(bookStatus);
    }
    //-------------------------------------------------------------------------------------------------
    
    @Override
    protected ArrayList<BookStatus> getModel(){
        return UserInSystem.getAccount().getBookStatuses();
    }
    
    //Метод, изменяющий значения учетной записи, для указания того, что книгу вернули в библиотеку
    private void closeStatus(BookStatus bookStatus){
        bookStatus.setTimeReturn(new GregorianCalendar());
        bookStatus.getBook().setStatus(Status.INSTOCK);
    }
}
