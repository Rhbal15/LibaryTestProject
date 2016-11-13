/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class BookStatusInterface {

    private Facade facade;
    private Scanner scanner;

    public BookStatusInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
    }
    
    public void working(){
         boolean end=false;
        while (!end) {
            System.out.println("Вы можете:\n1.Записать, что читатель взял книгу\n2.Записать, что читатель вернул книгу\n3.Вывести все учетрные записи пользователя\n0.Вернуться на предыдущий экран");
            String line = scanner.nextLine();
            switch (line) {
                case "1":
                    getBook();
                    break;
                case "2":
                    putBook();
                    break;
                case "3":
                    getBookStatuses();
                    break;
                case "0":
                    end=true;
                    break;
                default:
                    System.out.println("Неверно введено действие");
            }
        }
    }
    
    public void getBook(){
        System.out.println("Выберите читателя, который взял книгу");
        getAllReader();
        int numberReader= scanner.nextInt()-1;
        System.out.println("Выберите книгу, которую он взял");
        getAllBook();
        int numberBook= scanner.nextInt()-1;
        System.out.println("Выберите год, в который требуется вернуть книгу");
        int year= scanner.nextInt();
        System.out.println("Выберите месяц, в который требуется вернуть книгу");
        int month= scanner.nextInt();
        System.out.println("Выберите день, в который требуется вернуть книгу");
        int day= scanner.nextInt();
        try {
            facade.getBook(numberReader, numberBook, new GregorianCalendar(year, month, day));
        } catch (NoBookInLibaryException ex) {
            System.out.println("Книги нет в библиотеке");
        }
    }
    
    public void putBook(){
        System.out.println("Выберите читателя, который взял книгу");
        getAllReader();
        int numberReader= scanner.nextInt()-1;
        System.out.println("Выберите книгу, которую он взял");
        int numberBook= scanner.nextInt()-1;
        getAllBook();
        try {
            facade.putBook(numberReader,numberBook);
        } catch (BookInTakenNotFoundException ex) {
            System.out.println("Книга уже была взята");
        }
    }
    
    public void getBookStatuses(){
        System.out.println("Выберите читателя");
        getAllReader();
        int numberReader= scanner.nextInt()-1;
        
        for(int i=0;i<facade.getBookStatuses(numberReader).size();i++){
            System.out.println(i+1+" Взятая книга: "+facade.getBookStatuses(numberReader).get(i).getBook().getName()+
                    " Когда взята: "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.YEAR)+
                    " "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.MONTH)+
                    " "+facade.getBookStatuses(numberReader).get(i).getTimeReceipt().get(Calendar.DAY_OF_MONTH)+
                    " Когда нужно вернуть: "+facade.getBookStatuses(numberReader).get(i).getMustReturned().get(Calendar.YEAR)+
                    " "+facade.getBookStatuses(numberReader).get(i).getMustReturned().get(Calendar.MONTH)+
                    " "+facade.getBookStatuses(numberReader).get(i).getMustReturned().get(Calendar.DAY_OF_MONTH));
                    
        }
    }
    
    private void getAllBook() {
        for (int i = 0; i < facade.getAllBook().size(); i++) {
            System.out.println((i+1)+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre().getName()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }
    
    private void getAllReader() {
        for (int i = 0; i < facade.getAllAccounts().size(); i++) {
            System.out.println((i + 1) + " Фамилия: " + facade.getAllAccounts().get(i).getReader().getLastName()
                    + " Имя: " + facade.getAllAccounts().get(i).getReader().getFirstName() + " Отчество: "
                    + facade.getAllAccounts().get(i).getReader().getFirstName() + " Возраст: "
                    + facade.getAllAccounts().get(i).getReader().getAge());
        }
    }

}
