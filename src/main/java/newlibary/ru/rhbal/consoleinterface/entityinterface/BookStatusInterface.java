/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface.entityinterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.Workable;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.consoleinterface.command.Invoker;
import newlibary.ru.rhbal.consoleinterface.command.bookstatus.GetAll;
import newlibary.ru.rhbal.consoleinterface.command.bookstatus.GetBook;
import newlibary.ru.rhbal.consoleinterface.command.bookstatus.PutBook;
import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 * @author User
 */
public class BookStatusInterface extends AbstractInterface {

    @Override
    public String getName() {
        return "Работать с учетными записями";
    }

    @Override
    public ArrayList<Commandable> getCommand() {
        return new ArrayList<>(Arrays.asList(new GetAll(),new PutBook(),new GetBook()));
    }
}
