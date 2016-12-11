/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface.entityinterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.Workable;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.consoleinterface.command.Invoker;
import newlibary.ru.rhbal.consoleinterface.command.reader.Create;
import newlibary.ru.rhbal.consoleinterface.command.reader.Delete;
import newlibary.ru.rhbal.consoleinterface.command.reader.Edit;
import newlibary.ru.rhbal.consoleinterface.command.reader.GetAll;
import newlibary.ru.rhbal.facade.Facade;

/**
 * @author User
 */
public class ReaderInterface extends AbstractInterface {

    @Override
    public String getName() {
        return "Работать с читателями";
    }

    @Override
    public ArrayList<Commandable> getCommand() {
        return new ArrayList<>(Arrays.asList(new Create(),new Delete(),new Edit(),new GetAll()));
    }
}
