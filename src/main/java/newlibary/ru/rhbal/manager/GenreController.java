/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.util.ArrayList;
import newlibary.ru.rhbal.dao.DaoGenre;
import newlibary.ru.rhbal.entity.Genre;

/**
 *
 * @author User
 */
public class GenreController {
    private DaoGenre daoGenre;
    
    public GenreController(){
        daoGenre=new DaoGenre();
    }
    
    public void addGenre(String name){
        daoGenre.create(name);
    }
    
    public boolean deleteGenre(int id){
        return daoGenre.delete(id);
    }
    
    public void editGenre(int id, String newName){
        Genre genre= daoGenre.search(id);
        genre.setName(newName);
    }
    
    public ArrayList<Genre> getAllGenre(){
        return daoGenre.getAll();
    }
}
