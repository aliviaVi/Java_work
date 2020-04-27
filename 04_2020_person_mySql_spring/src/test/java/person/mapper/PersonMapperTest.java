package person.mapper;

import org.junit.jupiter.api.Test;
import person.dto.PersonDto;
import person.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMapperTest {

    PersonMapper personMapper=new PersonMapper();

@Test
    void shouldReturnPersonDto(){

    Person person=new Person(0,"Vasya","Pupkin", LocalDate.now().minusYears(25));
    PersonDto personDto=new PersonDto(0,"Vasya","Pupkin",LocalDate.now().minusYears(25));

    PersonDto mappedPersonToPersonDto = personMapper.mapPersonToPersonDto(person);

    assertEquals(personDto.firstName,mappedPersonToPersonDto.firstName);


}
}
