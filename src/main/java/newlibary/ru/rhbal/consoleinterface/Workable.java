package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;

/**
 * Created by User on 001 01.12.16.
 */
public interface Workable {
    void working() throws SQLException;
    String getName();
}
