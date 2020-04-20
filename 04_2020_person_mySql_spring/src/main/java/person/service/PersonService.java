package person.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.dto.NumberDto;
import person.dto.PersonDto;
import person.model.Person;
import person.model.PhoneNumber;
import person.repository.INumberRepository;
import person.repository.IPersonRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final String PERSON_NOT_FOUND= "Person not found";
    final IPersonRepository personRepository;
    final INumberRepository numberRepository;

    public PersonService(IPersonRepository personRepository, INumberRepository numberRepository) {
        this.personRepository = personRepository;
        this.numberRepository = numberRepository;
    }

    public void add (PersonDto personDto){
        Person person=new Person(personDto.firstName,personDto.lastName,personDto.birthday);
        personRepository.save(person);

        personDto.numbers.stream()
                .map(numberIn->new PhoneNumber(numberIn.number,person))
                .forEach(numberRepository::save);
    }

    @Transactional
    public void edit(PersonDto personDto){
        Person person = personRepository.findById(personDto.id).orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));

        person.setName(personDto.firstName);
        person.setLastName(personDto.lastName);
        person.setBirthday(personDto.birthday);

        personRepository.save(person);
    }

    public PersonDto getById(int id){
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
        PersonDto personDto=new PersonDto(id,person.getName(),person.getLastName(),person.getBirthday());

        personDto.numbers=person.getNumbers().stream()
                .map(number->new NumberDto(number.getId(),number.getNumber(),number.getPerson().getId()))
                .collect(Collectors.toList());
        return personDto;
    }

    public void removeById(int id){
        personRepository.deleteById(id);
    }

    public List<PersonDto> getAll(){
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> new PersonDto(person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()))
                .collect(Collectors.toList());
    }

    public List<PersonDto> getAllByName(String name){
        List<Person> personsList= personRepository.findByName(name);
        return personsList.stream()
                .map(person -> new PersonDto(person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()))
                .collect(Collectors.toList());
    }

    public List<PersonDto> getAllConstrainedByAge(int min, int max){
        LocalDate earliestBirthday = LocalDate.now().minusYears(max);
        LocalDate latestBirthday = LocalDate.now().minusYears(min);

        return getAllConstrainedByBirthdays(earliestBirthday,latestBirthday);
    }

    private List<PersonDto> getAllConstrainedByBirthdays(LocalDate earliestBirthday, LocalDate latestBirthday) {
        List<Person> persons = personRepository.findByBirthdayBetweenCustom(earliestBirthday, latestBirthday);

        return persons.stream()
                .map(person -> new PersonDto(person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()))
                .collect(Collectors.toList());

    }
}
