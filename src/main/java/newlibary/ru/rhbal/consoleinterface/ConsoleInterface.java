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
        facade.addBook("������� ������", 1824, "�����", "������ ��������� ���������", 1799);
        facade.addBook("��������� �����", 1954, "�������", "���� ������� ���� ������", 1892);
    }

    private void unAutharizationAction() {
        System.out.println("�� ������:\n1.������������������\n2.����������������\n0.�����");
        String line = scanner.nextLine();
        switch(line){
            case "1": registration();
                break;
            case "2": authorization();
                break;
            case "0": System.exit(0);
                break;
            default: System.out.println("������� ������� ��������");
        }
    }
    
    private void authorizationAction() {
        System.out.println("�� ������:\n1.�������� � �������\n2.�������� � ����������"
                + "\n3.�������� � �������\n4.�������� � �������� ��������\n0.�����");
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
            default: System.out.println("������� ������� ��������");
        }
    }
    
    
    private void registration(){
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
    
    private void authorization(){
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
