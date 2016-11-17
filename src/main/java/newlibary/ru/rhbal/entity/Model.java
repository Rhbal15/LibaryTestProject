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
    private ArrayList<Author> authors;
    private ArrayList<Book> books;
    private ArrayList<Reader> readers;
    
    private static Model INSTANCE;
    
    private Model(){
        authors=new ArrayList<>();
        books=new ArrayList<>();
        readers=new ArrayList<>();
    }

    public static Model getINSTANCE() {
        if(INSTANCE==null)
            INSTANCE=new Model();
        return INSTANCE;
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
    
}
