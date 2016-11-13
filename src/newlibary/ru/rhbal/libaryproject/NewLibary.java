/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.libaryproject;

import java.util.GregorianCalendar;
import newlibary.ru.rhbal.consoleinterface.ConsoleInterface;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;



/**
 *
 * @author User
 */
public class NewLibary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UserAlreadyExistException, InterruptedException {
            ConsoleInterface ci = new ConsoleInterface();
            ci.consoleInterface();
        }

}
