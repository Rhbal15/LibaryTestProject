/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.dao.DaoAuthor;
import newlibary.ru.rhbal.entity.Author;

/**
 *
 * @author User
 */
public class AuthorController {
    private DaoAuthor daoAuthor;
    
    public AuthorController(){
        daoAuthor=new DaoAuthor();
    }
    
    public void addAuthor(String name,int age) throws SQLException{
        Author author = new Author(name, age);
        daoAuthor.create(author);
    }
    
    public boolean deleteAuthor(int id) throws SQLException{
        return daoAuthor.delete(daoAuthor.getById(id));
    }
    
    public void editAuthor(int id, String newName,int newAge) throws SQLException{
        Author author = daoAuthor.getById(id);
        author.setName(newName);
        author.setAge(newAge);
        daoAuthor.update(author);
    }
    
    public ArrayList<Author> getAll() throws SQLException{
        return daoAuthor.getAll();
    }
}
