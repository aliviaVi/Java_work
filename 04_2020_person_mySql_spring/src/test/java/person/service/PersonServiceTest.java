package person.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import person.dto.NumberDto;
import person.dto.PersonDto;
import person.mapper.NumberMapper;
import person.mapper.PersonMapper;
import person.model.Person;
import person.model.PhoneNumber;
import person.repository.INumberRepository;
import person.repository.IPersonRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    IPersonRepository personRepository;
    @Mock
    INumberRepository   numberRepository;
    @Mock
    PersonMapper personMapper;
    @Mock
    NumberMapper numberMapper;

    @InjectMocks
    PersonService personService;

    @Test
    void add_normalData_passesToRepo() {

        LocalDate birthday=LocalDate.now().minusYears(25);

        PersonDto personDto=new PersonDto(5,"Vasya", "Vasin",birthday);

        personService.add(personDto);

        verify(personRepository).save(argThat(person->
                person.getId()==0
        && person.getLastName().equals(personDto.lastName)
        && person.getName().equals(personDto.firstName)
        && birthday.equals(person.getBirthday())
                ));

    }

    @Test
    public void testAdd_personWithNumbers_1NumberSaved() {
        LocalDate birthday = LocalDate.now().minusYears(25);
        PersonDto personIn = new PersonDto(0, "Vasya", "Vasin", birthday);
        personIn.numbers = Arrays.asList(new NumberDto(0, "number1", 0));

        personService.add(personIn);
        ArgumentCaptor<Person> personCaptor=ArgumentCaptor.forClass(Person.class);
//here we catch all the persons that were as arguments while invoking personRepository.save(person)
        verify(personRepository).save(personCaptor.capture());

        List<Person> capturedPersons = personCaptor.getAllValues();
        assertEquals(1, capturedPersons.size());
        Person capturedPerson = capturedPersons.get(0);
//check the single captured person
        assertEquals(personIn.firstName, capturedPerson.getName());
        assertEquals(personIn.lastName, capturedPerson.getLastName());
        assertEquals(birthday, capturedPerson.getBirthday());

//check the single captured number
        verify(numberRepository, times(1)).save(any());
        verify(numberRepository, times(1)).save(argThat(
                number -> number.getNumber().equals("number1") &&
                        number.getPerson().equals(capturedPerson)
                )
        );
    }


    @Test
    void edit() {
        LocalDate birthday=LocalDate.now().minusYears(25);

        PersonDto personDto=new PersonDto(5,"Petya", "Vasinyanovich",LocalDate.now().minusYears(25));

        Person personFromDB=new Person(5,"Vasya","Vasin",birthday);
        when(personRepository.findById(personDto.id)).thenReturn(java.util.Optional.of(personFromDB));

        personService.edit(personDto);

        ArgumentCaptor<Person> argumentCaptor=ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(argumentCaptor.capture());
        assertEquals(5, argumentCaptor.getValue().getId());
        assertEquals("Vasinyanovich", argumentCaptor.getValue().getLastName());

    }

    @Test
    void edit_withException(){
        PersonDto personDto=new PersonDto(6,"Petya", "Vasinyanovich",LocalDate.now().minusYears(25));

      //  when(personService.edit(personDto).thenThrow(new EntityNotFoundException();
        doThrow(EntityNotFoundException.class).when(personRepository).findById(personDto.getId());
        personService.edit(personDto);
    }

    @Test
    void getById() {
        LocalDate birthday=LocalDate.now().minusYears(25);

        PersonDto personDto=new PersonDto(5,"Vasya","Vasin",LocalDate.now().minusYears(25));
        int id = personDto.getId();

        Person personFromDB=new Person(5,"Vasya","Vasin",birthday);

        when(personRepository.findById(personDto.id)).thenReturn(java.util.Optional.of(personFromDB));

        PersonDto personFounded = personService.getById(id);
        assertEquals(personFounded,personDto);
        assertEquals(personFounded.firstName, personDto.firstName);

       /* ArgumentCaptor<Person> personCaptor=ArgumentCaptor.forClass(Person.class);
        verify(personRepository).findById(personCaptor.capture());
        assertEquals(5, personCaptor.getValue().getId());
        assertEquals("Vasya",personCaptor.getValue().getName());*/


    }

    @Test
    void removeById() {
        LocalDate birthday=LocalDate.now().minusYears(25);

        PersonDto personDto=new PersonDto(5,"Vasya","Vasin",birthday);
        int id = personDto.getId();
        personService.removeById(id);
        List<PersonDto> personsFromService = personService.getAll();

        assertEquals(0,personsFromService.size());
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    void getAll() {
        LocalDate birthday=LocalDate.now().minusYears(25);

        Person expectedFromDB=new Person(5,"Vasya","Vasin",birthday);

        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFromDB));
        List<PersonDto> personDtoList = personService.getAll();

        assertEquals(1,personDtoList.size());
    }

    @Test
    void getAllByName() {
        LocalDate birthday=LocalDate.now().minusYears(25);

        List<Person> personsList=Arrays.asList(new Person(5,"Vasya","Vasin",birthday), new Person(2,"Vasya","Mock",LocalDate.now().minusYears(12)));
        String name="Vasya";
        when(personRepository.findByName(name)).thenReturn(personsList);

        List<PersonDto> allByNameFromService = personService.getAllByName(name);

        assertEquals(2,allByNameFromService.size());
        assertEquals(personsList.get(0).getName(),allByNameFromService.get(0).firstName);

    }

    @Test
    void getAllPersonsPhoneNumbers() {
        LocalDate birthday = LocalDate.now().minusYears(25);
        PersonDto personIn = new PersonDto(0, "Vasya", "Vasin", birthday);
        personIn.numbers = Arrays.asList(new NumberDto(0, "number1", 0));

        Person expectedFromDB=new Person(0,"Vasya","Vasin",birthday);
        expectedFromDB.setNumbers(Arrays.asList(new PhoneNumber("number1",expectedFromDB)));

        when(personRepository.findById(personIn.id)).thenReturn(java.util.Optional.of(expectedFromDB));

        List<NumberDto> allPersonsPhoneNumbers = personService.getAllPersonsPhoneNumbers(personIn.id);

        assertEquals(1,allPersonsPhoneNumbers.size());
        assertEquals(personIn.id,allPersonsPhoneNumbers.get(0).personId);
       // assertEquals(personIn.numbers.get(0),allPersonsPhoneNumbers.get(0));//does'n work


    }
}
