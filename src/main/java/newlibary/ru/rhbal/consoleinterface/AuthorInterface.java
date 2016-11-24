/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;

import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 * @author User
 */
public class AuthorInterface {
    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public AuthorInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll = new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end = false;
        while (!end) {
            System.out.println("Вы можете:\n1.Добавить писателя\n2.Удалить писателя\n3.Изменить писателя\n4.Вывести всех писателей\n0.Вернуться на предыдущий экран");
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
                    getterAll.getAllAuthor();
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
            System.out.println("Введите Фамилию: ");
            String lastName = scanner.nextLine();
            System.out.println("Введите год рожения: ");
            int age = new Integer(scanner.nextLine());

            facade.addAuthor(lastName, age);
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void delete() throws SQLException {
        try {
            System.out.println("Выберите номер пользователя, которого хотите удалить: ");
            getterAll.getAllAuthor();
            int number = new Integer(scanner.nextLine());
            facade.deleteAuthor(number);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void edit() throws SQLException {
        try {
            System.out.println("Выберите номер пользователя, которого хотите изменить: ");
            getterAll.getAllAuthor();
            int number = new Integer(scanner.nextLine());
            System.out.println("Введите новую Фамилию: ");
            String newLastName = scanner.nextLine();
            System.out.println("Введите другой Год Рождения: ");
            int newAge = new Integer(scanner.nextLine());

            facade.editAuthor(number, newLastName, newAge);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }
}
