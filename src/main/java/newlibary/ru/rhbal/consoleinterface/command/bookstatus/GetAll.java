package newlibary.ru.rhbal.consoleinterface.command.bookstatus;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by User on 028 28.11.16.
 */
public class GetAll implements Commandable {
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllReaders();
            int numberReader = ConsoleWorker.getInt("Выберите читателя");
            ConsoleWorker.getBookStatuses(numberReader);

        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {

    }

    @Override
    public String getName() {
        return "Вывести все учетрные записи пользователя";
    }
}
