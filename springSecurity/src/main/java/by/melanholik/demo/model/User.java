package by.melanholik.demo.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Column(name = "year_of_birth")
    @Min(0)
    private int yearOfBirth;

    @Column(name = "password")
    @NotNull
    @NotEmpty
    private String password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(int id, String name, int yearOfBirth, String password, String role) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
        this.role = role;
    }

    public User(String name, int yearOfBirth, String password, String role) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
