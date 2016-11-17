/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao.exception;

/**
 *
 * @author User
 */
public class BookInTakenNotFoundException extends DaoException{

    public BookInTakenNotFoundException() {
    }

    public BookInTakenNotFoundException(String message) {
        super(message);
    }
    
}
