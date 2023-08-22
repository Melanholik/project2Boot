package by.melanholik.springCourse.project2Boot.services;

import by.melanholik.springCourse.project2Boot.models.Book;
import by.melanholik.springCourse.project2Boot.models.Person;
import by.melanholik.springCourse.project2Boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public void add(Person person) {
        personRepository.save(person);
    }

    public Optional<Person> getById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            return person;
        }
        changInListBookIsOverdue(person.get().getList());
        return personRepository.findById(id);
    }

    @Transactional
    public void deleteById(int id) {
        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(value -> deleteAllBooks(value.getList()));
        personRepository.deleteById(id);
    }

    @Transactional
    public void update(Person person) {
        personRepository.save(person);
    }

    private void changInListBookIsOverdue(List<Book> books) {
        Date currentDate = new Date();
        long currentMilliseconds = currentDate.getTime();
        for (Book book : books) {
            long differenceInMilliseconds = currentMilliseconds - book.getTakeTame().getTime();
            long differenceInDays = differenceInMilliseconds / 86400000;
            book.setOverdue(differenceInDays > 10);
        }
    }

    private void deleteAllBooks(List<Book> books) {
        for (Book book : books) {
            book.setPerson(null);
            book.setTakeTame(null);
        }
    }
}
