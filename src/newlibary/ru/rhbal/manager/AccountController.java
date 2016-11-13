/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import java.util.ArrayList;
import newlibary.ru.rhbal.dao.DaoAccount;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.entity.Account;

/**
 *
 * @author User
 */
public class AccountController {
    private DaoAccount daoAccount;

    public AccountController() {
        daoAccount=new DaoAccount();
    }
    
    
    
    //-------------------------------------------------------------------------------------------------
    //Методы создания аккаунтов
    //-------------------------------------------------------------------------------------------------

    public void create(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        daoAccount.create(login,password, lastName, firstName, surname, age);
    }
    
    public void create(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        daoAccount.create(lastName, firstName, surname, age);
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    
    public void editReader(int number,String newLastName,String newFirstName, String newSurname, int newAge){
        //Находим по number кокнретный аккаунт
        Account account = daoAccount.getAll().get(number);
        
        //Меняем значения этого аккаунта
        account.getReader().setAge(newAge);
        account.getReader().setFirstName(newFirstName);
        account.getReader().setLastName(newLastName);
        account.getReader().setSurname(newSurname);
    }
    
    public void delete(int id){
        daoAccount.delete(id);
    }
    
    public void  login(String login, String password) throws UserNotFoundException, NotCorrectPasswordException{
        UserInSystem.setAccount(daoAccount.getAccount(login, password));
    }
    
    public void logout(){
        UserInSystem.setAccount(null);
    }
    
    public ArrayList<Account> getAll(){
        return daoAccount.getAll();
    }
    
}
