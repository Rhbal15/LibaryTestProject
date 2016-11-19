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
    
    public boolean deleteBook(int number) throws SQLException{
        return daoBook.delete(daoBook.getById(number));
    }
    
    public void editBook(int number, String newName,int release, String genreName, int authorId) throws SQLException{
        
        //������� �����, ������� ����� ������
        Book book=daoBook.getById(number);
        
        //������ ���������
        book.setName(newName);
        book.setRelease(release);
        book.setGenre(genreName);
        book.setAuthor(new DaoAuthor().getById(authorId));
    }
    
    //����� ��������� ��� �����, ������� �� ����� ������������ ��������� �������
    public ArrayList<Book> searchBookByName(String name) throws SQLException{
        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<Book> books = new ArrayList<>();
        
        //������ �������: 
        //name=���� string= ���� ��������� matches:true 
        Pattern pattern = Pattern.compile("[a-z A-Z�-��-�]*"+name+"[a-z A-Z�-��-�]*");
        Matcher matcher;
        
        //���������� ��� �����, �������� ��, ������� ������������ �������
        for(Book value: daoBook.getAll()){
            matcher=pattern.matcher(value.getName());
            if(matcher.matches())
                books.add(value);
        }
        
        return books;
    }
    
    //����� ��������� ��� �����, ������� �� ����� ������ ����� ������������ ��������� �������
    public ArrayList<Book> searchBookByAuthorName(String name) throws SQLException{
        //�������������� ������, � ������� ����� ���������� �����
        ArrayList<Book> books = new ArrayList<>();
        
        //������ �������: 
        //name=���� string= ���� ��������� matches:true 
        Pattern pattern = Pattern.compile("[a-z A-Z�-��-�]*"+name+"[a-z A-Z�-��-�]*");
        Matcher matcher;
        
        //���������� ��� �����, �������� ��, ������� ������������ �������
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
