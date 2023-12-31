package by.melanholik.springCourse.project2Boot.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(min = 2, message = "The 'name' field must contain at least 2 characters")
    private String name;

    @Column(name = "birthday_year")
    @NotNull(message = "People must have birthday")
    private int birthdayYear;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> list;

    public Person() {
    }

    public Person(int id, String name, int birthdayYear) {
        this.id = id;
        this.name = name;
        this.birthdayYear = birthdayYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }
}
