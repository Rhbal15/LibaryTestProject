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
public class Edit implements Commandable{
    Facade facade=new Facade();
    Author author;
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllAuthors();
            int number = ConsoleWorker.getInt("Выберите номер автора, которого хотите изменить: ");
            author=facade.getAuthorById(number);
            facade.editAuthor(number, ConsoleWorker.getString("Введите полное имя автора, которого хотите изменить: "), ConsoleWorker.getInt("Введите год рождения автора, которого хотите изменить: "));

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException{
        try {
            facade.editAuthor(author.getId(),author.getName(),author.getAge());
        } catch (EntityNotFoundException e) {
            System.out.println("Произошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Изменить автора";
    }
}
