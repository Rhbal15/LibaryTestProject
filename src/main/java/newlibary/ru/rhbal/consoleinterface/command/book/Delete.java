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
public class Delete implements Commandable {
    Facade facade=new Facade();
    Book book;
    @Override
    public void execute() throws SQLException {

        try {
            ConsoleWorker.getAllBookInStock();
            int number = ConsoleWorker.getInt("Введите книгу, которую хотите удалить: ");
            book=facade.getBookById(number);
            facade.deleteBook(number);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        facade.addBook(book.getName(),book.getRelease(),book.getGenre(),book.getAuthor().getId());
    }

    @Override
    public String getName() {
        return "Удалить книгу";
    }
}
