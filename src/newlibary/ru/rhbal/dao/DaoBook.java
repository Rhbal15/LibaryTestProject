/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.Model;
import newlibary.ru.rhbal.entity.Status;

/**
 *
 * @author User
 */
public class DaoBook extends AbstractDao<Book>{

    //ћетод возвращающий первую в списке книгу, имеющую определенное им€, котора€ находитс€ в библиотеке
    public Book getBook(String name){
        for(Book value: getModel()){
            if(value.getName().equals(name) && value.getStatus()==Status.INSTOCK)
                return value;
        }
        return null;
    }
    
        
    public Book getBook(int number){
        return getModel().get(number);
    }
    
    
    public Book create(String name, int release, String genreName, String authorName,int authorAge){
        
        //»нициализируем dao дл€ работы с автором и жанром
        DaoAuthor daoAuthor=new DaoAuthor();
        DaoGenre daoGenre=new DaoGenre();
        
        //создаем новую книгу, добавл€ем в модель и возвращаем еЄ
        Book book = new Book(daoAuthor.create(authorName,authorAge), name, release,daoGenre.create(genreName));
        getModel().add(book);
        return book;
    }
    
    //метод удалени€ книги, наход€щейс€ в библиотеке
    public boolean delete(String name){
        for(Book value: getModel()){
            if(value.getStatus()==Status.INSTOCK && name.equals(value.getName())){
                return getModel().remove(value);
            }
        }
        return false;
    }
    
    //ћетод дл€ удален€и книги, подразумеваетс€, что номер книги указывает на книги, наход€щуюс€ в 
    //библиотеке, если вы не уверены в этом используйте другой метод
    public boolean delete(int number){
        getModel().remove(number);
        return true;
    }
    
    @Override
    protected ArrayList<Book> getModel() {
        return Model.getINSTANCE().getBooks();
    }
    
}
