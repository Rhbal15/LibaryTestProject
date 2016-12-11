package newlibary.ru.rhbal.consoleinterface.command.bookstatus;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 * Created by User on 028 28.11.16.
 */
public class GetBook implements Commandable {
    Facade facade=new Facade();
    int id;
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllReaders();
            int numberReader = ConsoleWorker.getInt("Выберите читателя, который взял книгу");
            ConsoleWorker.getAllBookInStock();
            int numberBook = ConsoleWorker.getInt("Выберите книгу, которую он взял");
            int year = ConsoleWorker.getInt("Выберите год, в который требуется вернуть книгу");
            int month = ConsoleWorker.getInt("Выберите месяц, в который требуется вернуть книгу");
            int day = ConsoleWorker.getInt("Выберите день, в который требуется вернуть книгу");
            id=facade.getBook(numberReader, numberBook, new GregorianCalendar(year, month, day));
        } catch (EntityNotFoundException | BookInTakenNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.deleteBookStatus(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Поизошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Записать, что читатель взял книгу";
    }
}
