package person.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.dto.NumberDto;
import person.dto.PersonDto;
import person.model.Person;
import person.model.PhoneNumber;
import java.util.List;
import java.util.stream.Collectors;

import person.repository.INumberRepository;
import person.repository.IPersonRepository;

@Service
public class NumberService {

    private INumberRepository numberRepository;
    private IPersonRepository personRepository;

    public NumberService(INumberRepository numberRepository, IPersonRepository personRepository) {
        this.numberRepository = numberRepository;
        this.personRepository = personRepository;
    }

    public void add(NumberDto numberDto){

        int personId = numberDto.personId;
        Person person = personRepository.findById(personId).get();
        PhoneNumber phoneNumber=new PhoneNumber(numberDto.number,person);
        person.addNumber(phoneNumber);
    }

    @Transactional
    public void edit(NumberDto numberDto){
        PhoneNumber phoneNumber = numberRepository.findById(numberDto.id).get();
        phoneNumber.setNumber(numberDto.number);

        numberRepository.save(phoneNumber);
    }

    public void removeById(int id){
        numberRepository.deleteById(id);
    }

    public List<NumberDto> getAllPersonsPhoneNumbers(int personId){
        Person person = personRepository.findById(personId).get();
        List<PhoneNumber> numbers = person.getNumbers();

        List<NumberDto> numberDtos = numbers.stream()
                .map(phoneNumber -> new NumberDto(phoneNumber.getId(),
                        phoneNumber.getNumber(),
                        phoneNumber.getId()))
                .collect(Collectors.toList());

        return numberDtos;
    }

    public NumberDto getNumberById(int id){
        PhoneNumber phoneNumber = numberRepository.findById(id).get();

        NumberDto numberDto=new NumberDto(phoneNumber.getId(),
                phoneNumber.getNumber(),
                phoneNumber.getId());
        return numberDto;
    }
}
