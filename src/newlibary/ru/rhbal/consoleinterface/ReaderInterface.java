/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.util.Scanner;
import newlibary.ru.rhbal.dao.exception.UserAlreadyExistException;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class ReaderInterface {

    private Facade facade;
    private Scanner scanner;

    public ReaderInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
    }

    public void working() {
        boolean end=false;
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
                    getAll();
                    break;
                case "0":
                    end=true;
                    break;
                default:
                    System.out.println("Неверно введено действие");
            }
        }
    }

    private void create() {
        System.out.println("Введите Фамилию: ");
        String lastName = scanner.nextLine();
        System.out.println("Введите Имя: ");
        String firstName = scanner.nextLine();
        System.out.println("Введите Отчество: ");
        String surname = scanner.nextLine();
        System.out.println("Введите Возраст: ");
        int age = scanner.nextInt();
        try {
            facade.createAccount(lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println("Такой читатель уже существует");
        }
    }

    private void delete() {
        System.out.println("Выберите номер пользователя, которого хотите удалить: ");
        getAll();
        int number= scanner.nextInt()-1;
        facade.deleteAccount(number);
    }

    private void edit() {
        System.out.println("Выберите номер пользователя, которого хотите изменить: ");
        getAll();
        int number= scanner.nextInt()-1;
        System.out.println("Введите новую Фамилию: ");
        String newLastName = scanner.nextLine();
        System.out.println("Введите новое Имя: ");
        String newFirstName = scanner.nextLine();
        System.out.println("Введите новое Отчество: ");
        String newSurname = scanner.nextLine();
        System.out.println("Введите другой Возраст: ");
        int newAge = scanner.nextInt();
        facade.editAccount(number, newLastName, newFirstName, newSurname, newAge);
    }

    private void getAll() {
        for (int i = 0; i < facade.getAllAccounts().size(); i++) {
            System.out.println((i + 1) + " Фамилия: " + facade.getAllAccounts().get(i).getReader().getLastName()
                    + " Имя: " + facade.getAllAccounts().get(i).getReader().getFirstName() + " Отчество: "
                    + facade.getAllAccounts().get(i).getReader().getFirstName() + " Возраст: "
                    + facade.getAllAccounts().get(i).getReader().getAge());
        }
    }
}
