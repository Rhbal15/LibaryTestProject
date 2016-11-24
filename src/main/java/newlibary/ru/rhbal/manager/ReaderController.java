/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import newlibary.ru.rhbal.dao.DaoReader;
import newlibary.ru.rhbal.manager.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.manager.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 *
 * @author User
 */
public class ReaderController {

    DaoReader readerDao = new DaoReader();


    //-------------------------------------------------------------------------------------------------
    //РњРµС‚РѕРґС‹ СЃРѕР·РґР°РЅРёСЏ Р°РєРєР°СѓРЅС‚РѕРІ
    //-------------------------------------------------------------------------------------------------

    public void create(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        for(Reader reader: getAll()){
            if(login.equals(reader.getLogin())){
                throw new UserAlreadyExistException("Пользователь уже существует");
            }
        }
        readerDao.create(new Reader(login, password, lastName, firstName, surname, age));
    }

    public void create(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException, SQLException{
        for(Reader reader: getAll()){
            if(reader.getAge()==age && firstName.equals(reader.getFirstName()) &&
                    lastName.equals(reader.getLastName()) && surname.equals(reader.getSurname()))
                throw new UserAlreadyExistException("Пользователь уже существует");
        }
        readerDao.create(new Reader(lastName, firstName, surname, age));
    }

    //-------------------------------------------------------------------------------------------------

    public void editReader(int number,String newLastName,String newFirstName, String newSurname, int newAge) throws SQLException, EntityNotFoundException{
        //РќР°С…РѕРґРёРј РїРѕ number РєРѕРєРЅСЂРµС‚РЅС‹Р№ Р°РєРєР°СѓРЅС‚
        Reader reader = readerDao.getById(number);
        if(reader==null){
            throw new EntityNotFoundException("Читатель не найден, изменений не прозошло");
        }

        reader.setLastName(newLastName);
        reader.setFirstName(newFirstName);
        reader.setSurname(newSurname);
        reader.setAge(newAge);
        readerDao.update(reader);
    }

    public void delete(int id) throws SQLException, EntityNotFoundException{
        Reader reader = readerDao.getById(id);

        if(reader==null){
            throw new EntityNotFoundException("Читатель не найден, удаление не прозошло");
        }
        readerDao.delete(reader);
    }

    public void  login(String login, String password) throws UserNotFoundException, NotCorrectPasswordException, SQLException{
        for(Reader value: readerDao.getAll()){

            if(value.getLogin()!=null && value.getLogin().equals(login)){
                if(!value.getPassword().equals(password)){
                    throw new NotCorrectPasswordException("Неверный пароль");
                }

                UserInSystem.setReader(value);
                break;
            }
        }

        if(UserInSystem.getReader()== null)
            throw new UserNotFoundException("Пользователь не найден");
    }

    public void logout(){
        UserInSystem.setReader(null);
    }

    public ArrayList<Reader> getAll() throws SQLException{
        return readerDao.getAll();
    }

}
