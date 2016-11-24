/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 * @author User
 */
public class BookInterface {
    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public BookInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll = new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end = false;
        while (!end) {
            System.out.println("Вы можете:\n1.Добавить книгу\n2.Удалить книгу\n3.Изменить книгу\n4.Вывести все книги находящиеся в библиотеке\n0.Вернуться на предыдущий экран");
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
                    getterAll.getAllBookInStock();
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
            System.out.println("Введите название Книги: ");
            String name = scanner.nextLine();
            System.out.println("Введите год выпуска книги: ");
            int release = new Integer(scanner.nextLine());
            System.out.println("Введите Жанр книги: ");
            String genre = scanner.nextLine();
            getterAll.getAllAuthor();
            System.out.println("Выберите Автора: ");
            int authorId = new Integer(scanner.nextLine());
            facade.addBook(name, release, genre, authorId);
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void delete() throws SQLException {
        try {
            System.out.println("Введите книгу, которую хотите удалить: ");
            getterAll.getAllBook();
            int number = new Integer(scanner.nextLine());

            facade.deleteBook(number);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    private void edit() throws SQLException {
        try {
            System.out.println("Введите книгу, которую  хотите изменить: ");
            getterAll.getAllBook();
            int number = new Integer(scanner.nextLine());
            System.out.println("Введите новое название Книги: ");
            String name = scanner.nextLine();
            System.out.println("Введите год выпуска книги: ");
            int release = new Integer(scanner.nextLine());
            System.out.println("Введите Жанр книги: ");
            String genre = scanner.nextLine();
            getterAll.getAllAuthor();
            System.out.println("Введите имя Автора ");
            int authorId = new Integer(scanner.nextLine());
            facade.editBook(number, name, release, genre, authorId);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }


}
