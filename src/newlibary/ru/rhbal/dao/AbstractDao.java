/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class AbstractDao<E> {
    protected abstract ArrayList<E> getModel();
    
    public ArrayList<E> getAll(){
        return getModel();
    }
}
