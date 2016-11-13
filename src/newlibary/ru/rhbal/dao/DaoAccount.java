/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Account;
import newlibary.ru.rhbal.entity.Model;
import newlibary.ru.rhbal.entity.Reader;

/**
 *
 * @author User
 */
public class DaoAccount extends AbstractDao<Account>{

    DaoReader daoReader;

    public DaoAccount() {
        daoReader=new DaoReader();
    }
    
    
    //-------------------------------------------------------------------------------------------------
    //Методы создания аккаунтов
    //-------------------------------------------------------------------------------------------------

    //Метод для создания аккаунта с доступом к интерфейсу программы
    public Account create(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        
        //Создаем или находим читателя
        Reader reader =daoReader.create(lastName, firstName, surname, age);
        
        //Проверяем, нет ли уже аккаунта принадлежащего этому пользователю
        for(Account value: getModel()){
            if(reader.equals(value.getReader()) || login.equals(value.getLogin()))
                throw new UserAlreadyExistException();
        }
        
        //Если нет, то создаем аккаунт и записываем в модель
        Account account=new Account(login, password, reader);
        getModel().add(account);
        return account;
    }
    
    //Метод для создания аккаунта без доступа к интерфейсу программы
    public Account create(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        
        //Создаем или находим читателя
        Reader reader =daoReader.create(lastName, firstName, surname, age);
        
        //Проверяем, нет ли уже аккаунта принадлежащего этому пользователю
        for(Account value: getModel()){
            if(reader.equals(value.getReader()))
                throw new UserAlreadyExistException();
        }
        
        //Если нет, то создаем аккаунт и записываем в модель
        Account account=new Account(reader);
        getModel().add(account);
        return account;
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    //Метод для верификации пользователя
    public Account getAccount(String login, String password) throws UserNotFoundException, NotCorrectPasswordException{
        
        //Находим аккаунт, если его нет, выбрасываем исключение
        Account account=searchByLogin(login);
        if(account==null)
            throw new UserNotFoundException();
        
        //Проверяем пароль, если не совпал выбрасываем исключение
        if(account.getPassword().equals(password))
            return account;
        throw new NotCorrectPasswordException();
    }
    
    public boolean delete(String lastName,String firstName, String surname, int age){
        return getModel().remove(searchByReaderName(lastName, firstName, surname, age));
        
    }
    
    public boolean delete(String login){
        return getModel().remove(searchByLogin(login));
    }
    
    public boolean delete(int id){
        getModel().remove(id);
        return true;
    }

    @Override
    protected ArrayList<Account> getModel() {
        return Model.getINSTANCE().getAccounts();
    }
    
    public Account searchByReaderName(String lastName,String firstName, String surname, int age){
        
        //Проходим по аккаунтам, пытаемся найти аккаунт по ФИО и возрасту, если не нашли возвращаем null
        for(Account account: getModel()){
            if(lastName.equals(account.getReader().getLastName()) && 
                    firstName.equals(account.getReader().getFirstName()) &&
                    surname.equals(account.getReader().getSurname()) &&
                    age==account.getReader().getAge())
                return account;
        }
        return null;
    }
    
    //-------------------------------------------------------------------------------------------------
    //Методы поиска аккаунтов
    //-------------------------------------------------------------------------------------------------
    
    public Account searchByLogin(String login){
        //Проходим по аккаунтам, пытаемся найти аккаунт по логину, если не нашли возвращаем null
        for(Account account: getModel()){
            if(login.equals(account.getLogin()))
                return account;
        }
        return null;
    }
    //-------------------------------------------------------------------------------------------------
}
