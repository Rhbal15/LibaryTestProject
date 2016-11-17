/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import newlibary.ru.rhbal.dao.DaoAuthor;
import newlibary.ru.rhbal.dao.DaoBook;
import newlibary.ru.rhbal.dao.DaoGenre;
import newlibary.ru.rhbal.entity.Author;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Genre;

/**
 *
 * @author User
 */
public class BookController{
    private DaoBook daoBook;

    public BookController() {
        daoBook=new DaoBook();
    }
    
    public void addBook(String name, int release, String genreName, String authorName,int authorAge){
        daoBook.create(name, release,genreName, authorName, authorAge);
    }
    
    public boolean deleteBook(int number){
        return daoBook.delete(number);
    }
    
    public void editBook(int number, String newName,int release, String genreName, String authorName, int authorYear){
        
        //Находим или создаем автора
        DaoAuthor daoAuthor = new DaoAuthor();
        Author author = daoAuthor.create(authorName,authorYear);
        
        //Находим книгу, которую будем менять
        Book book=daoBook.getBook(number);
        
        //Вносим изменения
        book.setAuthor(author);
        book.setName(newName);
        book.setRelease(release);
        
    }
    
    //Метод находящий все книги, которые по имени соотвествуют заданному запросу
    public ArrayList<Book> searchBookByName(String name){
        //Инициализируем список, в который будем записывать книги
        ArrayList<Book> books = new ArrayList<>();
        
        //Пример шаблона: 
        //name=Игра string= Игра престолов matches:true 
        Pattern pattern = Pattern.compile("[a-z A-Zа-яА-я]*"+name+"[a-z A-Zа-яА-я]*");
        Matcher matcher;
        
        //Перебираем все книги, выбираем те, которые соответствую шаблону
        for(Book value: daoBook.getAll()){
            matcher=pattern.matcher(value.getName());
            if(matcher.matches())
                books.add(value);
        }
        
        return books;
    }
    
    //Метод находящий все книги, которые по имени автора книги соотвествуют заданному запросу
    public ArrayList<Book> searchBookByAuthorName(String name){
        //Инициализируем список, в который будем записывать книги
        ArrayList<Book> books = new ArrayList<>();
        
        //Пример шаблона: 
        //name=Игра string= Игра престолов matches:true 
        Pattern pattern = Pattern.compile("[a-z A-Zа-яА-я]*"+name+"[a-z A-Zа-яА-я]*");
        Matcher matcher;
        
        //Перебираем все книги, выбираем те, которые соответствую шаблону
        for(Book value: daoBook.getAll()){
            matcher=pattern.matcher(value.getAuthor().getName());
            if(matcher.matches())
                books.add(value);
        }
        return books;
    }
    
    public ArrayList<Book> getAllBook(){
        return daoBook.getAll();
    }
}
