/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.security.auth.spi.LoginModule;



//Сущность аакаунтов, здесь хранятся все данные о взятых и возвращенных книгах пользователя
public class Account {
    private Reader reader;
    private String login;
    private String password;
    private ArrayList<BookStatus> bookStatuses;

    public Account(String login, String password, Reader reader) {
        this.login=login;
        this.password=password;
        this.reader = reader;
        bookStatuses=new ArrayList<>();
    }
    
    public Account(Reader reader) {
        this.login="";
        this.password="";
        this.reader = reader;
        bookStatuses=new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.login);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<BookStatus> getBookStatuses() {
        return bookStatuses;
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
