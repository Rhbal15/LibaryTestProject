/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


//Класс, хранящий списки сущностей программы
public class Model {
    private ArrayList<Account> accounts;
    private ArrayList<Author> authors;
    private ArrayList<Book> books;
    private ArrayList<Reader> readers;
    private ArrayList<Genre> genres;
    
    private static Model INSTANCE;
    
    private Model(){
        accounts=new ArrayList<>();
        authors=new ArrayList<>();
        books=new ArrayList<>();
        readers=new ArrayList<>();
        genres=new ArrayList<>();
    }

    public static Model getINSTANCE() {
        if(INSTANCE==null)
            INSTANCE=new Model();
        return INSTANCE;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }
    
    
    
    
    
}
