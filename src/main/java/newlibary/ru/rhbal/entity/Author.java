/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.entity;




//Сущность авторов книг
public class Author extends Entity<Integer>{
    private int id;
    private String name;
    private int age;

    public Author(String name, int age) {
        this.name=name;
        this.age = age;
    }

    public Author() {
    }
    
    

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Integer getId() {
        return id;
    }
    
    
    
    
}
