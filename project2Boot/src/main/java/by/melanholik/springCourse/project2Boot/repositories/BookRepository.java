package by.melanholik.springCourse.project2Boot.repositories;

import by.melanholik.springCourse.project2Boot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByNameStartingWith(String neededStr);
}
