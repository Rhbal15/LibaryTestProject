/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;
import newlibary.ru.rhbal.dao.exception.NotCorrectPasswordException;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.dao.exception.UserNotFoundException;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.UserInSystem;

/**
 *
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
            } catch (SQLException e) {
                System.out.println("������ SQL-�������");
            }

        }

    }

    public void initialize() throws UserAlreadyExistException {
    }

    private void unAutharizationAction() throws SQLException {
        System.out.println("�� ������:\n1.������������������\n2.����������������\n0.�����");
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
                System.out.println("������� ������� ��������");
        }
    }

    private void authorizationAction() throws SQLException {
        System.out.println("�� ������:\n1.�������� � �������\n2.�������� � ����������"
                + "\n3.�������� � �������� ��������\n 4.�������� � ��������\n0.�����");
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
                System.out.println("������� ������� ��������");
        }
    }

    private void registration() throws SQLException {
        System.out.println("������� �����: ");
        String login = scanner.nextLine();
        System.out.println("������� ������: ");
        String password = scanner.nextLine();
        System.out.println("������� �������: ");
        String lastName = scanner.nextLine();
        System.out.println("������� ���: ");
        String firstName = scanner.nextLine();
        System.out.println("������� ��������: ");
        String surname = scanner.nextLine();
        System.out.println("������� �������: ");
        int age = scanner.nextInt();
        try {
            facade.createAccount(login, password, lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println("������������ ��� ����������!");
        }
    }

    private void authorization() throws SQLException {
        System.out.println("������� �����: ");
        String login = scanner.nextLine();
        System.out.println("������� ������: ");
        String password = scanner.nextLine();
        try {
            facade.login(login, password);
        } catch (UserNotFoundException ex) {
            System.out.println("������������ �� ������");
        } catch (NotCorrectPasswordException ex) {
            System.out.println("������ ������ �������");
        }
    }

}
