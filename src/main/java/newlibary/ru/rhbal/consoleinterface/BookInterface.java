/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.consoleinterface;

import java.util.Scanner;
import newlibary.ru.rhbal.facade.Facade;

/**
 *
 * @author User
 */
public class BookInterface {
    private Facade facade;
    private Scanner scanner;

    public BookInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
    }

    public void working() {
        boolean end=false;
        while (!end) {
            System.out.println("Вы можете:\n1.Добавить книгу\n2.Удалить книгу\n3.Изменить книгу\n4.Вывести все книги находящиеся в библиотеке\n0.Вернуться на предыдущий экран");
            String line = scanner.nextLine();
            switch (line) {
                case "1":
                    create();
                    break;
                case "2":
                    delete();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    getAll();
                    break;
                case "0":
                    end=true;
                    break;
                default:
                    System.out.println("Неверно введено действие");
            }
        }
    }

    private void create() {
        System.out.println("Введите название Книги: ");
        String name = scanner.nextLine();
        System.out.println("Введите год выпуска книги: ");
        int release = scanner.nextInt();
        System.out.println("Введите Жанр книги: ");
        String genre = scanner.nextLine();
        System.out.println("Введите имя Автора ");
        String authorName = scanner.nextLine();
        System.out.println("Введите год рождения Автора ");
        int authorYear=scanner.nextInt();
        facade.addBook(name, release, genre, authorName, authorYear);
    }

    private void delete() {
        System.out.println("Введите книгу, которую хотите удалить: ");
        getAll();
        int number= scanner.nextInt()-1;
        facade.deleteBook(number);
    }

    private void edit() {
        System.out.println("Введите книгу, которую  хотите изменить: ");
        getAll();
        int number= scanner.nextInt()-1;
        System.out.println("Введите новое название Книги: ");
        String name = scanner.nextLine();
        System.out.println("Введите год выпуска книги: ");
        int release = scanner.nextInt();
        System.out.println("Введите Жанр книги: ");
        String genre = scanner.nextLine();
        System.out.println("Введите имя Автора ");
        String authorName = scanner.nextLine();
        System.out.println("Введите год рождения Автора ");
        int authorYear=scanner.nextInt();
        facade.editBook(number, name, release, genre, authorName, authorYear);
    }

    private void getAll() {
        for (int i = 0; i < facade.getAllBook().size(); i++) {
            System.out.println((i+1)+" Название:"+facade.getAllBook().get(i).getName()+
                    " Автор: "+facade.getAllBook().get(i).getAuthor().getName()+
                    " Жанр: "+facade.getAllBook().get(i).getGenre()+
                    " Год выпуска: "+facade.getAllBook().get(i).getRelease());
        }
    }
}
