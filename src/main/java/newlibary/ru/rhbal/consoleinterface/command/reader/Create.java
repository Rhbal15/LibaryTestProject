package newlibary.ru.rhbal.consoleinterface.command.reader;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;
import newlibary.ru.rhbal.manager.exception.UserAlreadyExistException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Create implements Commandable {
    Facade facade=new Facade();
    private int id;
    @Override
    public void execute() throws SQLException {
        try {

            id=facade.createAccount(ConsoleWorker.getString("Введите Фамилию читателя: "), ConsoleWorker.getString("Введите Имя читателя: "),
                    ConsoleWorker.getString("Введите Отчество читателя: "), ConsoleWorker.getInt("Введите Возраст читателя: "));

        } catch (UserAlreadyExistException ex) {
            System.out.println("Такой читатель уже существует");
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.deleteAccount(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Поизошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Добавить читателя";
    }
}
