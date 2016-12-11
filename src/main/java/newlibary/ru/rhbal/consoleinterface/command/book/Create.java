package newlibary.ru.rhbal.consoleinterface.command.book;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Create implements Commandable {

    private Facade facade=new Facade();
    private int id;

    @Override
    public void execute() throws SQLException {
        try {
            String name = ConsoleWorker.getString("Введите название Книги: ");
            int release = ConsoleWorker.getInt("Введите год выпуска книги: ");
            String genre = ConsoleWorker.getString("Введите Жанр книги: ");
            ConsoleWorker.getAllAuthors();
            int authorId = ConsoleWorker.getInt("Выберите Автора: ");
            id=facade.addBook(name, release, genre, authorId);
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() {
        try {
            facade.deleteBook(id);
        } catch (SQLException | EntityNotFoundException e) {
            System.out.println("Произошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Добавить книгу";
    }
}
