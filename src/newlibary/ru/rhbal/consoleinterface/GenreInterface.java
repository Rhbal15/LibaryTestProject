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
public class GenreInterface {
    private Facade facade;
    private Scanner scanner;

    public GenreInterface() {
        facade = new Facade();
        scanner = new Scanner(System.in);
    }

    public void working() {
        boolean end=false;
        while (!end) {
            System.out.println("Вы можете:\n1.Добавить жанр\n2.Удалить жанр\n3.Изменить жанр\n4.Вывести все жанры\n0.Вернуться на предыдущий экран");
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
        System.out.println("Введите название Жанра: ");
        String name = scanner.nextLine();
        facade.addGenre(name);
    }

    private void delete() {
        System.out.println("Введите название Жанра, который хотите удалить: ");
        getAll();
        int number= scanner.nextInt()-1;
        facade.deleteGenre(number);
    }

    private void edit() {
        System.out.println("Введите название Жанра, который хотите изменить: ");
        getAll();
        int number= scanner.nextInt()-1;
        System.out.println("Введите новое название Жанра: ");
        String name = scanner.nextLine();
        facade.editGenre(number, name);
    }

    private void getAll() {
        for (int i = 0; i < facade.getAllGenre().size(); i++) {
            System.out.println((i+1)+" Название:"+facade.getAllGenre().get(i).getName());
        }
    }
}
