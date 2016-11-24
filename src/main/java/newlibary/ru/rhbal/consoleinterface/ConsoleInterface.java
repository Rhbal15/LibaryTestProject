/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;

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
    private Scanner scanner;

    public ConsoleInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
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
        System.out.println("Вы можете:\n1.Зарегистрироваться\n2.Авторизироваться\n0.Выйти");
        String line = scanner.nextLine();
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
        System.out.println("Вы можете:\n1.Работать с книгами\n2.Работать с читателями"
                + "\n3.Работать с учетными записями\n4.Работать с авторами\n0.Выйти");
        String line = scanner.nextLine();
        switch (line) {
            case "1":
                new BookInterface().working();
                break;
            case "2":
                new ReaderInterface().working();
                break;
            case "3":
                new BookStatusInterface().working();
                break;
            case "4":
                new AuthorInterface().working();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Неверно введено действие");
        }
    }

    private void registration() throws SQLException {
        try {
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            System.out.println("Введите Фамилию: ");
            String lastName = scanner.nextLine();
            System.out.println("Введите Имя: ");
            String firstName = scanner.nextLine();
            System.out.println("Введите Отчество: ");
            String surname = scanner.nextLine();
            System.out.println("Введите Возраст: ");
            int age = new Integer(scanner.nextLine());

            facade.createAccount(login, password, lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void authorization() throws SQLException {
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        try {
            facade.login(login, password);
        } catch (UserNotFoundException | NotCorrectPasswordException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
