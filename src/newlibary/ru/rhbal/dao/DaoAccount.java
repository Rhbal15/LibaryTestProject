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
    //������ �������� ���������
    //-------------------------------------------------------------------------------------------------

    //����� ��� �������� �������� � �������� � ���������� ���������
    public Account create(String login, String password,String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        
        //������� ��� ������� ��������
        Reader reader =daoReader.create(lastName, firstName, surname, age);
        
        //���������, ��� �� ��� �������� �������������� ����� ������������
        for(Account value: getModel()){
            if(reader.equals(value.getReader()) || login.equals(value.getLogin()))
                throw new UserAlreadyExistException();
        }
        
        //���� ���, �� ������� ������� � ���������� � ������
        Account account=new Account(login, password, reader);
        getModel().add(account);
        return account;
    }
    
    //����� ��� �������� �������� ��� ������� � ���������� ���������
    public Account create(String lastName,String firstName, String surname, int age) throws UserAlreadyExistException{
        
        //������� ��� ������� ��������
        Reader reader =daoReader.create(lastName, firstName, surname, age);
        
        //���������, ��� �� ��� �������� �������������� ����� ������������
        for(Account value: getModel()){
            if(reader.equals(value.getReader()))
                throw new UserAlreadyExistException();
        }
        
        //���� ���, �� ������� ������� � ���������� � ������
        Account account=new Account(reader);
        getModel().add(account);
        return account;
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    //����� ��� ����������� ������������
    public Account getAccount(String login, String password) throws UserNotFoundException, NotCorrectPasswordException{
        
        //������� �������, ���� ��� ���, ����������� ����������
        Account account=searchByLogin(login);
        if(account==null)
            throw new UserNotFoundException();
        
        //��������� ������, ���� �� ������ ����������� ����������
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
        
        //�������� �� ���������, �������� ����� ������� �� ��� � ��������, ���� �� ����� ���������� null
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
    //������ ������ ���������
    //-------------------------------------------------------------------------------------------------
    
    public Account searchByLogin(String login){
        //�������� �� ���������, �������� ����� ������� �� ������, ���� �� ����� ���������� null
        for(Account account: getModel()){
            if(login.equals(account.getLogin()))
                return account;
        }
        return null;
    }
    //-------------------------------------------------------------------------------------------------
}
