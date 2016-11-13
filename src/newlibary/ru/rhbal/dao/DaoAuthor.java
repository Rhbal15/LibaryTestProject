/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Author;
import newlibary.ru.rhbal.entity.Model;

/**
 *
 * @author User
 */
public class DaoAuthor extends AbstractDao<Author>{
    
    //Метод для описка автора по ФИО
    public Author searchByName(String name){
        
        //Ищем автора без учета регистра, если нет возвращаем null
        for(Author value: getModel()){
            if(name.toLowerCase().equals(value.getName().toLowerCase()))
                return value;
        }
        return null;
    }
    
    //Метод, который создает нового или находит соответствующего заданным параметрам автора
    public Author create(String name, int age){
        
        //Проверяем, нет ли уже такого автора без учета регистра, если есть возвращаем его
        for(Author value: getModel()){
            if(value.getAge()==age && name.toLowerCase().equals(value.getName().toLowerCase()))
                return value;
        }
        
        //Если нет, то создаем нового
        Author author = new Author(name, age);
        getModel().add(author);
        return author;
    }

    @Override
    protected ArrayList<Author> getModel() {
        return Model.getINSTANCE().getAuthors();
    }
}
