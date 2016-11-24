/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.sql.SQLException;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class GetterAll {
    Facade facade;

    public GetterAll(Facade facade) {
        this.facade=facade;
    }


    public void getAllBook() throws SQLException {
        for (int i = 0; i < facade.getAllBook().size(); i++) {
            System.out.println(facade.getAllBook().get(i).getId()+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }

    public void getAllBookInStock() throws SQLException {
        for (int i = 0; i < facade.getBookInStock().size(); i++) {
            System.out.println(facade.getAllBook().get(i).getId()+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }

    public void getAllReader() throws SQLException {
        for (int i = 0; i < facade.getAllReader().size(); i++) {
            System.out.println(facade.getAllReader().get(i).getId() + " Фамилия: " + facade.getAllReader().get(i).getLastName()
                    + " Имя: " + facade.getAllReader().get(i).getFirstName() + " Отчество: "
                    + facade.getAllReader().get(i).getFirstName() + " Возраст: "
                    + facade.getAllReader().get(i).getAge());
        }
    }

    public void getAllAuthor() throws SQLException{
        for(int i=0;i<facade.getAllAuthors().size();i++){
            System.out.println(facade.getAllAuthors().get(i).getId()+
                    "Имя автора: "+facade.getAllAuthors().get(i).getName()+
                    "Годж рождения: "+facade.getAllAuthors().get(i).getAge()
            );
        }
    }

}
