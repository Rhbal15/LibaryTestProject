/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;




//Сущность книг
public class Book extends Entity<Integer>{
    private int id;
    private Author author;
    private String name;
    private int release;
    private Status status;
    private String genre;

    public Book(Author author, String name, int release, String genre) {
        this.author = author;
        this.name = name;
        this.release = release;
        this.status=Status.INSTOCK;
        this.genre=genre;
    }

    public Book() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getRelease() {
        return release;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public Integer getId() {
        return id;
    }

}
