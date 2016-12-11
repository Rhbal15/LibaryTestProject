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
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 *
 * @author User
 */
public class AuthorController {
    private DaoAuthor daoAuthor;

    public AuthorController(){
        daoAuthor=new DaoAuthor();
    }

    public int addAuthor(String name,int age) throws SQLException{
        Author author = new Author(name, age);
        return daoAuthor.create(author);
    }

    public boolean deleteAuthor(int id) throws SQLException, EntityNotFoundException{
        Author author=daoAuthor.getById(id);
        if(author==null){
            throw new EntityNotFoundException("Автор не найден, удаления не проиошло");
        }

        return daoAuthor.delete(author);
    }

    public void editAuthor(int id, String newName,int newAge) throws SQLException, EntityNotFoundException{
        Author author = daoAuthor.getById(id);

        if(author==null){
            throw new EntityNotFoundException("Автор не найден, изменений не произошло");
        }

        author.setName(newName);
        author.setAge(newAge);
        daoAuthor.update(author);
    }

    public ArrayList<Author> getAll() throws SQLException{
        return daoAuthor.getAll();
    }

    public Author getAuthorById(int id) throws SQLException {
        return daoAuthor.getById(id);
    }
}
