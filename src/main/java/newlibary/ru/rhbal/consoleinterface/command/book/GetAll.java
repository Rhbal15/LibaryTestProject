package newlibary.ru.rhbal.consoleinterface.command.book;

import newlibary.ru.rhbal.consoleinterface.command.Commandable;
import newlibary.ru.rhbal.facade.Facade;

import java.sql.SQLException;

/**
 * Created by User on 027 27.11.16.
 */
public class GetAll implements Commandable {

    Facade facade=new Facade();

    @Override
    public void execute() throws SQLException {

        for (int i = 0; i < facade.getAllBook().size(); i++) {
            System.out.println(facade.getAllBook().get(i).getId()+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }

    @Override
    public void redo() {

    }

    @Override
    public String getName() {
        return "Вывести все книги пользователя";
    }
}
