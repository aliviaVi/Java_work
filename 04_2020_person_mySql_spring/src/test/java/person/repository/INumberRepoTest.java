package person.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import person.dto.NumberDto;
import person.model.Person;
import person.model.PhoneNumber;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class INumberRepoTest {

    @Autowired
    INumberRepository numberRepository;
    @Autowired
    TestEntityManager entityManager;



    @Test
    void test_findByPersonId_should_return_number_by_person_id(){
        NumberDto numberDto=new NumberDto(1,"number1",2);
        Person person=new Person(2,"Vasya","Pupkin", LocalDate.now().minusYears(23));

        PhoneNumber phoneNumber=new PhoneNumber(numberDto.number,person);

        numberRepository.save(phoneNumber);

        List<PhoneNumber> byPersonId = numberRepository.findByPersonId(numberDto.id);

        assertEquals(1,byPersonId.size());

    }
}
