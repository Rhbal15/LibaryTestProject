/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import newlibary.ru.rhbal.entity.Genre;
import newlibary.ru.rhbal.entity.Model;

/**
 *
 * @author User
 */
public class DaoGenre extends AbstractDao<Genre>{

    public Genre create(String name){
        Genre genre=search(name);
        if(genre!=null){
            return genre;
        }
        
        genre= new Genre(name);
        getModel().add(genre);
        return genre;
    }
    
    //Удаление жанра по имени
    public boolean delete(String name){
        return getModel().remove(search(name));
    }
    
    //Удаление жанра по id
    public boolean delete(int id){
        getModel().remove(id);
        return true;
    }
    
    @Override
    protected ArrayList<Genre> getModel() {
        return Model.getINSTANCE().getGenres();
    }
    
    //Поиск жанра по имени
    public Genre search(String name){
        for(Genre value: getModel()){
            if(name.toLowerCase().equals(value.getName().toLowerCase()))
                return value;
        }
        return null;
    }
    
    public Genre search(int id){
        return getModel().get(id);
    }
    
}
