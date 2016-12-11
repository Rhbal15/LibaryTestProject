package newlibary.ru.rhbal.consoleinterface.command;

import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by User on 027 27.11.16.
 */
public class Invoker {
    private static Invoker INSTANCE=new Invoker();
    private Stack<Commandable> commandStack=new Stack<>();

    private Invoker(){}

    public static void execute(Commandable command) throws SQLException {
        command.execute();
        INSTANCE.commandStack.push(command);
    }

    public  static void redo() throws SQLException{
        try {
            Commandable command = INSTANCE.commandStack.pop();
            command.redo();
        }catch (EmptyStackException e){}
    }
}
