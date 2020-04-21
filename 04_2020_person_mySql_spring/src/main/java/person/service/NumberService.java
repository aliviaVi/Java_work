package person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.dto.NumberDto;
import person.mapper.NumberMapper;
import person.model.Person;
import person.model.PhoneNumber;
import person.repository.INumberRepository;
import person.repository.IPersonRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class NumberService {

    private static final String NUMBER_NOT_FOUND= "Phone number not found";
    private INumberRepository numberRepository;
    private IPersonRepository personRepository;
    @Autowired
    private NumberMapper numberMapper;

    public NumberService(INumberRepository numberRepository, IPersonRepository personRepository) {
        this.numberRepository = numberRepository;
        this.personRepository = personRepository;
    }

    public void add(NumberDto numberDto){

        int personId = numberDto.personId;
        Person person = personRepository.findById(personId).get();
        PhoneNumber phoneNumber=new PhoneNumber(numberDto.number,person);
       // person.addNumber(phoneNumber);
        numberRepository.save(phoneNumber);
    }

    @Transactional
    public void edit(NumberDto numberDto){
        PhoneNumber phoneNumber = numberRepository.findById(numberDto.id).orElseThrow(()->new EntityNotFoundException(NUMBER_NOT_FOUND));
        phoneNumber.setNumber(numberDto.number);

        numberRepository.save(phoneNumber);
    }

    public void removeById(int id){
        numberRepository.deleteById(id);
    }



    public NumberDto getNumberById(int id){
        PhoneNumber phoneNumber = numberRepository.findById(id).orElseThrow(()->new EntityNotFoundException(NUMBER_NOT_FOUND));

        return numberMapper.mapPhoneNumberToNumberDto(phoneNumber);

    }
}
