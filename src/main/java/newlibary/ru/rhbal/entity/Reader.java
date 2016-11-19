/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;

import java.util.ArrayList;
import java.util.Objects;


//Сущность читателей
public class Reader extends Entity<Integer>{
    private int id;
    private String lastName;
    private String firstName;
    private String surname;
    private int age;
    private ArrayList<BookStatus> bookStatuses;
    private String login;
    private String password;

    public Reader(String LastName, String FirstName, String Surname, int age) {
        this.lastName = LastName;
        this.firstName = FirstName;
        this.surname = Surname;
        this.age = age;
        bookStatuses=new ArrayList<>();
    }
    
    public Reader(String login, String password, String LastName, String FirstName, String Surname, int age) {
        this.login=login;
        this.password=password;
        this.lastName = LastName;
        this.firstName = FirstName;
        this.surname = Surname;
        this.age = age;
        bookStatuses=new ArrayList<>();
    }

    public Reader() {
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<BookStatus> getBookStatuses() {
        return bookStatuses;
    }

    public void setBookStatuses(ArrayList<BookStatus> bookStatuses) {
        this.bookStatuses = bookStatuses;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
