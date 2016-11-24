/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 * @author User
 */
public class BookStatusInterface {

    private Facade facade;
    private Scanner scanner;
    private GetterAll getterAll;

    public BookStatusInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
        getterAll = new GetterAll(facade);
    }

    public void working() throws SQLException {
        boolean end = false;
        while (!end) {
            System.out.println("Вы можете:\n1.Записать, что читатель взял книгу\n2.Записать, что читатель вернул книгу\n3.Вывести все учетрные записи пользователя\n0.Вернуться на предыдущий экран");
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
                    end = true;
                    break;
                default:
                    System.out.println("Неверно введено действие");
            }
        }
    }

    public void getBook() throws SQLException {
        try {
            System.out.println("Выберите читателя, который взял книгу");
            getterAll.getAllReader();
            int numberReader = new Integer(scanner.nextLine());
            System.out.println("Выберите книгу, которую он взял");
            getterAll.getAllBookInStock();
            int numberBook = new Integer(scanner.nextLine());
            System.out.println("Выберите год, в который требуется вернуть книгу");
            int year = new Integer(scanner.nextLine());
            System.out.println("Выберите месяц, в который требуется вернуть книгу");
            int month = new Integer(scanner.nextLine());
            System.out.println("Выберите день, в который требуется вернуть книгу");
            int day = new Integer(scanner.nextLine());
            facade.getBook(numberReader, numberBook, new GregorianCalendar(year, month, day));
        } catch (EntityNotFoundException | BookInTakenNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    public void putBook() throws SQLException {
        System.out.println("Выберите читателя, который взял книгу");
        getterAll.getAllReader();
        int numberReader = new Integer(scanner.nextLine());

        System.out.println("Выберите книгу, которую он вернул");

        for (int i = 0; i < facade.getBookStatuses(numberReader).size(); i++) {
            if (facade.getBookStatuses(numberReader).get(i).getTimeReturn() == null)
                System.out.println(facade.getBookStatuses(numberReader).get(i).getId() + " Взятая книга: " + facade.getBookStatuses(numberReader).get(i).getBook().getName() +
                        " Когда взята: " + facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.YEAR) +
                        " " + facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.MONTH) +
                        " " + facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.DAY_OF_MONTH) +
                        " Когда нужно вернуть: " + facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.YEAR) +
                        " " + facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.MONTH) +
                        " " + facade.getBookStatuses(numberReader).get(i).getMustBeReturned().get(Calendar.DAY_OF_MONTH));

        }
        try {
            int numberBook = new Integer(scanner.nextLine());
            facade.putBook(numberBook);
        } catch (BookInTakenNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    public void getBookStatuses() throws SQLException {
        System.out.println("Выберите читателя");
        getterAll.getAllReader();
        try {
            int numberReader = new Integer(scanner.nextLine());
            for (int i = 0; i < facade.getBookIndividualReader(numberReader).size(); i++) {
                System.out.println(facade.getBookIndividualReader(numberReader).get(i).getBook().getId() +
                        " Взятая книга: " + facade.getBookIndividualReader(numberReader).get(i).getBook().getName() +
                        " Когда взята: " + facade.getBookIndividualReader(numberReader).get(i).getTimeReceipt().get(Calendar.YEAR) +
                        " " + facade.getBookIndividualReader(numberReader).get(i).getTimeReceipt().get(Calendar.MONTH) +
                        " " + facade.getBookIndividualReader(numberReader).get(i).getTimeReceipt().get(Calendar.DAY_OF_MONTH) +
                        " Когда нужно вернуть: " + facade.getBookIndividualReader(numberReader).get(i).getMustBeReturned().get(Calendar.YEAR) +
                        " " + facade.getBookIndividualReader(numberReader).get(i).getMustBeReturned().get(Calendar.MONTH) +
                        " " + facade.getBookIndividualReader(numberReader).get(i).getMustBeReturned().get(Calendar.DAY_OF_MONTH));

            }
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

}
