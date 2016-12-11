package newlibary.ru.rhbal.consoleinterface.command.bookstatus;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by User on 028 28.11.16.
 */
public class PutBook implements Commandable {
    Facade facade=new Facade();
    int numberBook;
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllReaders();
            int numberReader = ConsoleWorker.getInt("Выберите читателя, который взял книгу");
            ConsoleWorker.getBookStatuses(numberReader);
            numberBook = ConsoleWorker.getInt("Выберите книгу, которую он вернул");
            facade.putBook(numberBook);
        } catch (BookInTakenNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.putBookRemove(numberBook);
        } catch (BookInTakenNotFoundException e) {
            System.out.println("Поизошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Записать, что читатель вернул книгу";
    }
}
