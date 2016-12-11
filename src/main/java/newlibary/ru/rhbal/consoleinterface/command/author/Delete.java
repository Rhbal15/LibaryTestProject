package newlibary.ru.rhbal.consoleinterface.command.author;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.entity.Author;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Delete implements Commandable {
    Facade facade = new Facade();
    Author author;
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllAuthors();
            int number = ConsoleWorker.getInt("Выберите номер автора, которого хотите удалить: ");
            author=facade.getAuthorById(number);
            facade.deleteAuthor(number);

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        facade.addAuthor(author.getName(),author.getAge());
    }

    @Override
    public String getName() {
        return "Удалить автора";
    }
}
