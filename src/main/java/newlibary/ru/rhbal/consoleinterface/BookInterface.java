/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Scanner;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class BookInterface {
    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public BookInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll=new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end=false;
        while (!end) {
            System.out.println("�� ������:\n1.�������� �����\n2.������� �����\n3.�������� �����\n4.������� ��� ����� ����������� � ����������\n0.��������� �� ���������� �����");
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
                    getterAll.getAllBook();
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
        System.out.println("������� �������� �����: ");
        String name = scanner.nextLine();
        System.out.println("������� ��� ������� �����: ");
        int release = scanner.nextInt();
        System.out.println("������� ���� �����: ");
        String genre = scanner.nextLine();
        getterAll.getAllAuthor();
        System.out.println("�������� ������: ");
        int authorId = scanner.nextInt();
        facade.addBook(name, release, genre, authorId);
    }

    private void delete() throws SQLException {
        System.out.println("������� �����, ������� ������ �������: ");
        getterAll.getAllBook();
        int number= scanner.nextInt()-1;
        facade.deleteBook(number);
    }

    private void edit() throws SQLException {
        System.out.println("������� �����, �������  ������ ��������: ");
        getterAll.getAllBook();
        int number= scanner.nextInt()-1;
        System.out.println("������� ����� �������� �����: ");
        String name = scanner.nextLine();
        System.out.println("������� ��� ������� �����: ");
        int release = scanner.nextInt();
        System.out.println("������� ���� �����: ");
        String genre = scanner.nextLine();
        getterAll.getAllAuthor();
        System.out.println("������� ��� ������ ");
        int authorId = scanner.nextInt();
        facade.editBook(number, name, release, genre, authorId);
    }

    
}
