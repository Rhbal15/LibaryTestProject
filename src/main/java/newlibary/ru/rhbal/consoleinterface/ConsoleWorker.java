package newlibary.ru.rhbal.consoleinterface;

import newlibary.ru.rhbal.facade.Facade;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by User on 027 27.11.16.
 */
public class ConsoleWorker {
    private static Scanner scanner=new Scanner(System.in);
    private static Facade facade = new Facade();

    public static String getString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int getInt(String message){
        System.out.println(message);
        return new Integer(scanner.nextLine());
    }

    public static void getAllAuthors() throws SQLException {
        for (int i = 0; i < facade.getAllAuthors().size(); i++) {
            System.out.println(facade.getAllAuthors().get(i).getId() +
                    " Имя автора: " + facade.getAllAuthors().get(i).getName() +
                    " Год рождения: " + facade.getAllAuthors().get(i).getAge());
        }
    }

    public static void getAllReaders() throws SQLException {
        for (int i = 0; i < facade.getAllReaders().size(); i++) {
            System.out.println(facade.getAllReaders().get(i).getId() + " Фамилия: " + facade.getAllReaders().get(i).getLastName()
                    + " Имя: " + facade.getAllReaders().get(i).getFirstName() + " Отчество: "
                    + facade.getAllReaders().get(i).getFirstName() + " Возраст: "
                    + facade.getAllReaders().get(i).getAge());
        }
    }

    public static void getAllBookInStock() throws SQLException {
        for (int i = 0; i < facade.getBookInStock().size(); i++) {
            System.out.println(facade.getAllBook().get(i).getId()+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }

    public static void getBookStatuses(int numberReader) throws SQLException {
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
    }
}
