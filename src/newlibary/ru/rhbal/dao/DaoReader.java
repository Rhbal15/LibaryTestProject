/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.entity.Model;
import newlibary.ru.rhbal.entity.Reader;

/**
 *
 * @author User
 */
public class DaoReader extends AbstractDao<Reader>{
    
    //Создание читателя, предполагается, что оно используется только при создании аккаунтов,
    //поэтому не проводится проверка на уникальность
    public Reader create(String lastName,String firstName, String surname, int age){
       Reader reader=new Reader(lastName, firstName, surname, age);
       getModel().add(reader);
       return reader;
    }
    
    public boolean delete(String lastName,String firstName, String surname, int age){
        return getModel().remove(search(lastName, firstName, surname, age));
    }
    
    
    @Override
    protected ArrayList<Reader> getModel(){
        return Model.getINSTANCE().getReaders();
    }
    
    //Поиск читателя по ФИО и возрасту, если он не найден возврашается null
    public Reader search(String lastName,String firstName, String surname, int age){
        for(Reader value: getModel()){
            if(lastName.equals(value.getLastName()) &&
                    firstName.equals(value.getFirstName()) &&
                    surname.equals(value.getSurname()) &&
                    age==value.getAge())
                return value;
        }
        return null;
    }
}
