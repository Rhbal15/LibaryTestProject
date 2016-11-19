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
            System.out.println(facade.getAllBook().get(i).getId()+" ��������:"+facade.getAllBook().get(i).getName()+
                    " �����: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " ����: "+facade.getAllBook().get(i).getGenre()+
                    " ��� �������: "+facade.getAllBook().get(i).getRelease());
        }
    }
    
    public void getAllReader() throws SQLException {
        for (int i = 0; i < facade.getAllReader().size(); i++) {
            System.out.println(facade.getAllReader().get(i).getId() + " �������: " + facade.getAllReader().get(i).getLastName()
                    + " ���: " + facade.getAllReader().get(i).getFirstName() + " ��������: "
                    + facade.getAllReader().get(i).getFirstName() + " �������: "
                    + facade.getAllReader().get(i).getAge());
        }
    }
    
    public void getAllAuthor() throws SQLException{
        for(int i=0;i<facade.getAllAuthors().size();i++){
            System.out.println(facade.getAllAuthors().get(i).getId()+
                    "��� ������: "+facade.getAllAuthors().get(i).getName()+
                    "���� ��������: "+facade.getAllAuthors().get(i).getAge()
            );
        }
    }
}
