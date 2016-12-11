package newlibary.ru.rhbal.consoleinterface.command.book;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 028 28.11.16.
 */
public class Edit implements Commandable {
    Facade facade=new Facade();
    Book book;
    @Override
    public void execute() throws SQLException {
        try {

            ConsoleWorker.getAllBookInStock();
            int number = ConsoleWorker.getInt("Введите книгу, которую  хотите изменить: ");
            book=facade.getBookById(number);
            String name = ConsoleWorker.getString("Введите новое название Книги: ");
            int release = ConsoleWorker.getInt("Введите год выпуска книги: ");
            String genre = ConsoleWorker.getString("Введите Жанр книги: ");
            ConsoleWorker.getAllAuthors();
            int authorId = ConsoleWorker.getInt("Введите номер автора книги");
            facade.editBook(number, name, release, genre, authorId);

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.editBook(book.getId(),book.getName(),book.getRelease(),book.getGenre(),book.getAuthor().getId());
        } catch (EntityNotFoundException e) {
            System.out.println("Произошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Изменить книгу";
    }
}
