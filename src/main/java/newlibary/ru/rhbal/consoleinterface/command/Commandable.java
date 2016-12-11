package newlibary.ru.rhbal.consoleinterface.command;

import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public interface Commandable  {
    void execute() throws SQLException;
    void redo() throws SQLException;
    String getName();
}
