/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.libaryproject;

import java.io.PrintStream;
import java.util.Arrays;

import newlibary.ru.rhbal.consoleinterface.ConsoleInterface;
import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;

/**
 * @author User
 */
public class NewLibary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UserAlreadyExistException, InterruptedException {
        try {
            ConsoleInterface ci = new ConsoleInterface();
            ci.consoleInterface();

        } catch (Exception ex) {
            System.err.println("Непредвиденное исключение, пожалуйста обратитесь к разработчику");
            System.err.println(ex.getClass());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

}
