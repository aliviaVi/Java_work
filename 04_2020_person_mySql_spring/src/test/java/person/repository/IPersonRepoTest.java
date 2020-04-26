package person.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import person.model.Person;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPersonRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IPersonRepository personRepository;

    @Test
    public void testFindByName_oneRecord_found() {
        LocalDate kotusBirthday = LocalDate.now().minusYears(11);
        Person person = new Person("Vasya", "Kotov", LocalDate.now().minusYears(11));

        entityManager.persist(person);
        entityManager.flush();

        List<Person> foundPersons = personRepository.findByName("Vasya");
        assertEquals(1, foundPersons.size());

        assertEquals("Vasya", foundPersons.get(0).getName());
        assertEquals("Kotov", foundPersons.get(0).getLastName());
        assertEquals(kotusBirthday, foundPersons.get(0).getBirthday());
    }

    @Test
    void findAll() {
        LocalDate kotusBirthday = LocalDate.now().minusYears(11);
        Person person = new Person("Vasya", "Kotov", LocalDate.now().minusYears(11));

        personRepository.save(person);

        List<Person> all = personRepository.findAll();
        assertNotNull(person);
        assertTrue(all.size()==1);

    }

    @Test
    void findByBirthdayBetweenCustom() {
        LocalDate kotusBirthday = LocalDate.now().minusYears(11);
        Person person = new Person("Vasya", "Kotov", kotusBirthday);

        Person person2 = new Person("Vasya", "Kotov", LocalDate.now().minusYears(13));

        personRepository.save(person);
        personRepository.save(person2);

        List<Person> byBirthdayBetweenCustom = personRepository.findByBirthdayBetweenCustom(LocalDate.now().minusYears(20), LocalDate.now().plusYears(20));

        assertEquals(2,byBirthdayBetweenCustom.size());

    }

    @Test
    void removeWithLastNameStarting() {
        LocalDate kotusBirthday = LocalDate.now().minusYears(11);
        Person person = new Person("Vasya", "Kotov", kotusBirthday);

        entityManager.persist(person);
        entityManager.flush();

        personRepository.removeWithLastNameStarting("Ko");

        List<Person> all = personRepository.findAll();

        assertEquals(0,all.size());

    }
}
