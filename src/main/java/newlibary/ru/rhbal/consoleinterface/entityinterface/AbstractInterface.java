package newlibary.ru.rhbal.consoleinterface.entityinterface;

import newlibary.ru.rhbal.consoleinterface.ConsoleWorker;
import newlibary.ru.rhbal.consoleinterface.Workable;
import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.consoleinterface.command.Invoker;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 001 01.12.16.
 */
public abstract class AbstractInterface implements Workable {

    public void working() throws SQLException {
        boolean end = false;
        while (!end) {
            try {

                ArrayList<Commandable> commandables = getCommand();
                String line = "Вы можете:\n";

                int i;
                for (i = 0; i < commandables.size(); i++) {
                    line = line.concat((i + 1) + "." + commandables.get(i).getName() + "\n");
                }
                line = line.concat((i + 1) + ".Отменить действие\n0.Назад");

                int lineInt = ConsoleWorker.getInt(line);

                if (lineInt == 0) {
                    end=true;
                }

                if (lineInt > 0 && lineInt < (commandables.size() + 1)) {
                    Invoker.execute(commandables.get(lineInt - 1).getClass().newInstance());
                }
                if (lineInt == (commandables.size() + 1)) {
                    Invoker.redo();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Неверно введен символ");
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("Не удалось получить команду");
            }
        }
    }

    public abstract ArrayList<Commandable> getCommand();
}
