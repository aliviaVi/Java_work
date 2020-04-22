package person.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import person.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface IPersonRepository extends CrudRepository<Person,Integer> {

    public List<Person> findAll();

    public List<Person> findByName(String name);

    public List<Person> findByBirthdayBetween(LocalDate earliestBirthday, LocalDate latestBirthday);

    @Query("select p from Person p where p.birthday>=:after and p.birthday<=:before")
    public List<Person> findByBirthdayBetweenCustom(@Param("after") LocalDate after, @Param("before") LocalDate before);

    @Transactional
    @Modifying
    @Query("delete from Person p where p.lastName like ?1%")
    public void removeWithLastNameStarting(String pattern);
}
