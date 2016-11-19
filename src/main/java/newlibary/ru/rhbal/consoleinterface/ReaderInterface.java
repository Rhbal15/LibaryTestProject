/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
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
    private GetterAll getterAll;

    public ReaderInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll=new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end=false;
        while (!end) {
            System.out.println("�� ������:\n1.�������� ��������\n2.������� ��������\n3.�������� ��������\n4.������� ���� ���������\n0.��������� �� ���������� �����");
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
                    end=true;
                    break;
                default:
                    System.out.println("������� ������� ��������");
            }
        }
    }

    private void create() throws SQLException {
        System.out.println("������� ������� ��������: ");
        String lastName = scanner.nextLine();
        System.out.println("������� ��� ��������: ");
        String firstName = scanner.nextLine();
        System.out.println("������� �������� ��������: ");
        String surname = scanner.nextLine();
        System.out.println("������� ������� ��������: ");
        int age = scanner.nextInt();
        try {
            facade.createAccount(lastName, firstName, surname, age);
        } catch (UserAlreadyExistException ex) {
            System.out.println("����� �������� ��� ����������");
        }
    }

    private void delete() throws SQLException {
        System.out.println("������� ����� ��������, �������� ������ �������: ");
        getterAll.getAllReader();
        int number= scanner.nextInt();
        facade.deleteAccount(number);
    }

    private void edit() throws SQLException {
        System.out.println("������� ����� ��������, �������� ������ ��������: ");
        getterAll.getAllReader();
        int number= scanner.nextInt();
        System.out.println("������� ����� ������� ��������: ");
        String newLastName = scanner.nextLine();
        System.out.println("������� ����� ��� ��������: ");
        String newFirstName = scanner.nextLine();
        System.out.println("������� ����� �������� ��������: ");
        String newSurname = scanner.nextLine();
        System.out.println("������� ����� ������� ��������: ");
        int newAge = scanner.nextInt();
        facade.editAccount(number, newLastName, newFirstName, newSurname, newAge);
    }

    
}
