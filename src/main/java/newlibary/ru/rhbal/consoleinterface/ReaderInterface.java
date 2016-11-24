/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;

import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 * @author User
 */
public class ReaderInterface {

    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public ReaderInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll = new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end = false;
        while (!end) {
            System.out.println("Вы можете:\n1.Добавить читателя\n2.Удалить читателя\n3.Изменить читателя\n4.Вывести всех читателей\n0.Вернуться на предыдущий экран");
            String line = scanner.nextLine();
            switch (line) {
                case "1":
                    create();
                    break;
                case "2":
                    delete();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    getterAll.getAllReader();
                    break;
                case "0":
                    end = true;
                    break;
                default:
                    System.out.println("Неверно введено действие");
            }
        }
    }

    private void create() throws SQLException {
        try {
            System.out.println("Введите Фамилию читателя: ");
            String lastName = scanner.nextLine();
            System.out.println("Введите Имя читателя: ");
            String firstName = scanner.nextLine();
            System.out.println("Введите Отчество читателя: ");
            String surname = scanner.nextLine();
            System.out.println("Введите возраст читателя: ");
            int age = new Integer(scanner.nextLine());

            facade.createAccount(lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println("Такой читатель уже существует");
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void delete() throws SQLException {
        try {
            System.out.println("Введите номер читателя, которого хотите удалить: ");
            getterAll.getAllReader();
            int number = new Integer(scanner.nextLine());
            facade.deleteAccount(number);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void edit() throws SQLException {
        try {
            System.out.println("Введите номер читателя, которого хотите изменить: ");
            getterAll.getAllReader();
            int number = new Integer(scanner.nextLine());
            System.out.println("Введите новую Фамилию читателя: ");
            String newLastName = scanner.nextLine();
            System.out.println("Введите новое Имя читателя: ");
            String newFirstName = scanner.nextLine();
            System.out.println("Введите новое Отчество читателя: ");
            String newSurname = scanner.nextLine();
            System.out.println("Введите новый возраст читателя: ");
            int newAge = scanner.nextInt();
            facade.editAccount(number, newLastName, newFirstName, newSurname, newAge);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }


}
