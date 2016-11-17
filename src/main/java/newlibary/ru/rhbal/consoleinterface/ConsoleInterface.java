/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        scanner=new Scanner(System.in);
    }

    
    public void consoleInterface() {
        try {
            initialize();
        } catch (UserAlreadyExistException ex) {
        }
        
        while (true) {

            if (UserInSystem.getAccount()!=null) {
                authorizationAction();
            } else {
                unAutharizationAction();
            }
               
        }

    }
    
    public void initialize() throws UserAlreadyExistException{
        facade.createAccount("User", "123456", "Test", "Test", "Test", 20);
        facade.addBook("Евгений Онегин", 1824, "Роман", "Пушкин Александр Сергеевич", 1799);
        facade.addBook("Властелин колец", 1954, "Фэнтези", "Джон Рональд Руэл Толкин", 1892);
    }

    private void unAutharizationAction() {
        System.out.println("Вы можете:\n1.Зарегистрироваться\n2.Авторизироваться\n0.Выйти");
        String line = scanner.nextLine();
        switch(line){
            case "1": registration();
                break;
            case "2": authorization();
                break;
            case "0": System.exit(0);
                break;
            default: System.out.println("Неверно введено действие");
        }
    }
    
    private void authorizationAction() {
        System.out.println("Вы можете:\n1.Работать с книгами\n2.Работать с читателями"
                + "\n3.Работать с жанрами\n4.Работать с учетными записями\n0.Выйти");
        String line = scanner.nextLine();
        switch(line){
            case "1": 
                new BookInterface().working();
                break;
            case "2": 
                new ReaderInterface().working();
                break;
            case "3": 
                new GenreInterface().working();
                break;
            case "4": 
                new BookStatusInterface().working();
                break;
            case "0": System.exit(0);
                break;
            default: System.out.println("Неверно введено действие");
        }
    }
    
    
    private void registration(){
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
        int age = scanner.nextInt();
        try {
            facade.createAccount(login, password, lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println("Пользователь уже существует!");
        }
    }
    
    private void authorization(){
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        try {
            facade.login(login, password);
        } catch (UserNotFoundException ex) {
            System.out.println("Пользователь не найден");
        } catch (NotCorrectPasswordException ex) {
            System.out.println("Пароль введен неверно");
        }
    }

}
