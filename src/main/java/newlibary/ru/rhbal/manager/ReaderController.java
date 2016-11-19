/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.dao.DaoReader;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Reader;

/**
 *
 * @author User
 */
public class ReaderController {
    
    DaoReader readerDao = new DaoReader();
    
    
    //-------------------------------------------------------------------------------------------------
    //Методы создания аккаунтов
    //-------------------------------------------------------------------------------------------------

    public void create(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        readerDao.create(new Reader(login, password, lastName, firstName, surname, age));
    }
    
    public void create(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        readerDao.create(new Reader(lastName, firstName, surname, age));
    }
    
    //-------------------------------------------------------------------------------------------------
    
    public void editReader(int number,String newLastName,String newFirstName, String newSurname, int newAge) throws SQLException{
        //Находим по number кокнретный аккаунт
        Reader reader = readerDao.getById(number);
        
        reader.setLastName(newLastName);
        reader.setFirstName(newFirstName);
        reader.setSurname(newSurname);
        reader.setAge(newAge);
        readerDao.update(reader);
    }
    
    public void delete(int id) throws SQLException{
        System.out.println(readerDao.getById(id));
                
        readerDao.delete(readerDao.getById(id));
    }
    
    public void  login(String login, String password) throws UserNotFoundException, NotCorrectPasswordException, SQLException{
        for(Reader value: readerDao.getAll()){
            
            if(value.getLogin()!=null && value.getLogin().equals(login)){
                if(!value.getPassword().equals(password)){
                    throw new NotCorrectPasswordException();
                }
                
                UserInSystem.setReader(value);
                break;
            }
        }
    }
    
    public void logout(){
        UserInSystem.setReader(null);
    }
    
    public ArrayList<Reader> getAll() throws SQLException{
        return readerDao.getAll();
    }
    
}
