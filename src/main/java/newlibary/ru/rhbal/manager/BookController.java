/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import newlibary.ru.rhbal.dao.DaoAuthor;
import newlibary.ru.rhbal.dao.DaoBook;
import newlibary.ru.rhbal.entity.Author;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 *
 * @author User
 */
public class BookController{
    private DaoBook daoBook;

    public BookController() {
        daoBook=new DaoBook();
    }

    public void addBook(String name, int release, String genreName, int authorId) throws SQLException{
        daoBook.create(new Book(new DaoAuthor().getById(authorId), name, release,genreName));
    }

    public boolean deleteBook(int number) throws SQLException, EntityNotFoundException{
        Book book = daoBook.getById(number);
        if(book==null){
            throw new EntityNotFoundException("Книга не найдена, удаление не произошло");
        }

        return daoBook.delete(book);
    }

    public void editBook(int number, String newName,int release, String genreName, int authorId) throws SQLException, EntityNotFoundException{

        //Находим книгу, которую будем менять
        Book book=daoBook.getById(number);
        if(book==null){
            throw new EntityNotFoundException("Книга не найден, изменений не произошло");
        }

        //Вносим изменения
        book.setName(newName);
        book.setRelease(release);
        book.setGenre(genreName);
        book.setAuthor(new DaoAuthor().getById(authorId));
    }

    //Метод находящий все книги, которые по имени соотвествуют заданному запросу
    public ArrayList<Book> searchBookByName(String name) throws SQLException{
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
    public ArrayList<Book> searchBookByAuthorName(String name) throws SQLException{
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

    public ArrayList<Book> getAllBook() throws SQLException{
        return daoBook.getAll();
    }
}
