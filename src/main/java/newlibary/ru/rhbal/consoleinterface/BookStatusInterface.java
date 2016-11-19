/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class BookStatusInterface {

    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public BookStatusInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll= new GetterAll(facade);
    }
    
    public void working() throws SQLException{
         boolean end=false;
        while (!end) {
            System.out.println("�� ������:\n1.��������, ��� �������� ���� �����\n2.��������, ��� �������� ������ �����\n3.������� ��� �������� ������ ������������\n0.��������� �� ���������� �����");
            String line = scanner.nextLine();
            switch (line) {
                case "1":
                    getBook();
                    break;
                case "2":
                    putBook();
                    break;
                case "3":
                    getBookStatuses();
                    break;
                case "0":
                    end=true;
                    break;
                default:
                    System.out.println("������� ������� ��������");
            }
        }
    }
    
    public void getBook() throws SQLException{
        System.out.println("�������� ��������, ������� ���� �����");
        getterAll.getAllReader();
        int numberReader= scanner.nextInt();
        System.out.println("�������� �����, ������� �� ����");
        getterAll.getAllBook();
        int numberBook= scanner.nextInt();
        System.out.println("�������� ���, � ������� ��������� ������� �����");
        int year= scanner.nextInt();
        System.out.println("�������� �����, � ������� ��������� ������� �����");
        int month= scanner.nextInt();
        System.out.println("�������� ����, � ������� ��������� ������� �����");
        int day= scanner.nextInt();
        try {
            facade.getBook(numberReader, numberBook, new GregorianCalendar(year, month, day));
        } catch (NoBookInLibaryException ex) {
            System.out.println("����� ��� � ����������");
        }
    }
    
    public void putBook() throws SQLException{
        System.out.println("�������� ��������, ������� ���� �����");
        getterAll.getAllReader();
        int numberReader= scanner.nextInt();
        System.out.println("�������� �����, ������� �� ����");
        int numberBook= scanner.nextInt();
        getterAll.getAllBook();
        try {
            facade.putBook(numberBook);
        } catch (BookInTakenNotFoundException ex) {
            System.out.println("����� ��� ���� �����");
        }
    }
    
    public void getBookStatuses() throws SQLException{
        System.out.println("�������� ��������");
        getterAll.getAllReader();
        int numberReader= scanner.nextInt();
        
        for(int i=0;i<facade.getBookStatuses(numberReader).size();i++){
            System.out.println(i+1+" ������ �����: "+facade.getBookStatuses(numberReader).get(i).getBook().getName()+
                    " ����� �����: "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.YEAR)+
                    " "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.MONTH)+
                    " "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.DAY_OF_MONTH)+
                    " ����� ����� �������: "+facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.YEAR)+
                    " "+facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.MONTH)+
                    " "+facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.DAY_OF_MONTH));
                    
        }
    }

}
