package newlibary.ru.rhbal.consoleinterface.command.reader;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.entity.Reader;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class Edit implements Commandable {
    Facade facade=new Facade();
    Reader reader;
    @Override
    public void execute() throws SQLException {
        try {
            ConsoleWorker.getAllReaders();
            int number= ConsoleWorker.getInt("Выберите номер читателя, которого хотите изменить: ");
            reader=facade.getReaderById(number);
            facade.editAccount(number, ConsoleWorker.getString("Введите Фамилию читателя: "), ConsoleWorker.getString("Введите Имя читателя: "),
                    ConsoleWorker.getString("Введите Отчество читателя: "), ConsoleWorker.getInt("Введите Возраст читателя: "));

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Неверно введен символ");
        }
    }

    @Override
    public void redo() throws SQLException {
        try {
            facade.editAccount(reader.getId(),reader.getLastName(),reader.getFirstName(),reader.getSurname(),reader.getAge());
        } catch (EntityNotFoundException e) {
            System.out.println("Произошла ошибка при отмене действия");
        }
    }

    @Override
    public String getName() {
        return "Изменить читателя";
    }
}
