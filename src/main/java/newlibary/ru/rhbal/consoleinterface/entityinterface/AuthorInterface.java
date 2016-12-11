/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface.entityinterface;


import java.util.ArrayList;
import java.util.Arrays;

import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.consoleinterface.command.author.Create;
import newlibary.ru.rhbal.consoleinterface.command.author.Delete;
import newlibary.ru.rhbal.consoleinterface.command.author.Edit;
import newlibary.ru.rhbal.consoleinterface.command.author.GetAll;

/**
 * @author User
 */
public class AuthorInterface extends AbstractInterface {

    @Override
    public ArrayList<Commandable> getCommand() {
        return new ArrayList<>(Arrays.asList(new Create(),new Delete(),new Edit(),new GetAll()));
    }

    @Override
    public String getName() {
        return "Работать с авторами";
    }
}
