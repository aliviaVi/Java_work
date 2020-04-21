package person.mapper;


import org.springframework.stereotype.Component;
import person.dto.PersonDto;
import person.model.Person;

@Component
public class PersonMapper {


    public PersonDto mapPersonToPersonDto(Person person){
        return new PersonDto(person.getId(),
                person.getName(),
                person.getLastName(),
                person.getBirthday());
    }
}
