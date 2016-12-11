package newlibary.ru.rhbal.consoleinterface.command.author;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class GetAll implements Commandable {

    @Override
    public void execute() throws SQLException {
        ConsoleWorker.getAllAuthors();
    }

    @Override
    public void redo() throws SQLException {

    }

    @Override
    public String getName() {
        return "Вывести всех авторов";
    }
}
