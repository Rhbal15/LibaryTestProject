/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager.exception;

/**
 *
 * @author User
 */
public class NotCorrectPasswordException extends Exception{

    public NotCorrectPasswordException() {
    }

    public NotCorrectPasswordException(String message) {
        super(message);
    }

}
