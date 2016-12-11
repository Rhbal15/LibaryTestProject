/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import newlibary.ru.rhbal.consoleinterface.entityinterface.*;
import newlibary.ru.rhbal.manager.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.manager.exception.UserNotFoundException;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.UserInSystem;

/**
 * @author User
 */
public class ConsoleInterface {

    private Facade facade;
    private ArrayList<AbstractInterface> workables;

    public ConsoleInterface() {
        facade = new Facade();
        workables =  new ArrayList<>(Arrays.asList(new AuthorInterface(),new BookInterface(),new BookStatusInterface(),new ReaderInterface()));

    }

    public void consoleInterface() {
        try {
            initialize();
        } catch (UserAlreadyExistException ex) {
        }

        while (true) {
            try {
                if (UserInSystem.getReader() != null) {
                    authorizationAction();
                } else {
                    unAutharizationAction();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public void initialize() throws UserAlreadyExistException {
    }

    private void unAutharizationAction() throws SQLException {
        String line = ConsoleWorker.getString("Вы можете:\n1.Зарегистрироваться\n2.Авторизироваться\n0.Выйти");
        switch (line) {
            case "1":
                registration();
                break;
            case "2":
                authorization();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Неверно введено действие");
        }
    }

    private void authorizationAction() throws SQLException {
        try {
            String line = "Вы можете:\n";

            for (int i = 0; i < workables.size(); i++) {
                line = line.concat((i + 1) + "." + workables.get(i).getName() + "\n");
            }
            line = line.concat("0.Выход");

            int lineInt = ConsoleWorker.getInt(line);

            if (lineInt == 0) {
                System.exit(0);
            }
            if (lineInt > 0 && lineInt < (workables.size() + 1)) {
                workables.get(lineInt - 1).working();
            }
        }catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void registration() throws SQLException {
        try {
            String login = ConsoleWorker.getString("Введите логин: ");
            String password = ConsoleWorker.getString("Введите пароль: ");
            String lastName = ConsoleWorker.getString("Введите Фамилию: ");
            String firstName = ConsoleWorker.getString("Введите Имя: ");
            String surname = ConsoleWorker.getString("Введите Отчество: ");
            int age = ConsoleWorker.getInt("Введите Возраст: ");
            facade.createAccount(login, password, lastName, firstName, surname, age);

        } catch (UserAlreadyExistException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void authorization() throws SQLException {
        try {
            String login = ConsoleWorker.getString("Введите логин: ");
            String password = ConsoleWorker.getString("Введите пароль: ");
            facade.login(login, password);
        } catch (UserNotFoundException | NotCorrectPasswordException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
