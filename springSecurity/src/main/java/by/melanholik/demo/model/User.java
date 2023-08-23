package by.melanholik.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "year_of_birth")
    @NotNull
    private int yearOfBirth;

    @Column(name = "password")
    @NotNull
    private String password;

    public User() {
    }

    public User(int id, String name, int yearOfBirth, String password) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }

    public User(String name, int yearOfBirth, String password) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
