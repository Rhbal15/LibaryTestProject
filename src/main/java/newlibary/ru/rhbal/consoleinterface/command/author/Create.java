package newlibary.ru.rhbal.consoleinterface.command.author;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Create implements Commandable{
    private Facade facade = new Facade();
    private int id;
    @Override
    public void execute() throws SQLException {
        try {
            id=facade.addAuthor(ConsoleWorker.getString("Введите полное имя автора, которого хотите добавить: "), ConsoleWorker.getInt("Введите год рождения автора, которого хотите добавить: "));
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.deleteAuthor(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Поизошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Добавить автора";
    }
}
