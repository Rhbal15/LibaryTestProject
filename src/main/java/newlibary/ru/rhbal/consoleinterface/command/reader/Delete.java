package newlibary.ru.rhbal.consoleinterface.command.reader;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;
import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Delete implements Commandable {
    Facade facade=new Facade();
    Reader reader;
    
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllReaders();
            int number = ConsoleWorker.getInt("Выберите номер читателя, которого хотите удалить: ");
            reader=facade.getReaderById(number);
            facade.deleteAccount(number);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            if(reader.getLogin()!=null)
                facade.createAccount(reader.getLogin(),reader.getPassword(),reader.getLastName(),reader.getFirstName(),reader.getSurname(),reader.getAge());
            else {
                facade.createAccount(reader.getLastName(),reader.getFirstName(),reader.getSurname(),reader.getAge());
            }
        } catch (UserAlreadyExistException e) {
            System.out.println("Поизошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Удалить читателя";
    }
}
