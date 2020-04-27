package person.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import person.dto.NumberDto;
import person.mapper.NumberMapper;
import person.mapper.PersonMapper;
import person.model.Person;
import person.model.PhoneNumber;
import person.repository.INumberRepository;
import person.repository.IPersonRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NumberServiceTest {

    @Mock
    INumberRepository numberRepository;
    @Mock
    IPersonRepository personRepository;
    @Spy
    PersonMapper personMapper= new PersonMapper();
    @Spy
    NumberMapper numberMapper=new NumberMapper();

    @InjectMocks
    NumberService numberService;


    @Test
    void test_add_should_add_number(){
        NumberDto numberDto=new NumberDto(1,"number1",2);
        Person person=new Person(2,"Vasya","Pupkin", LocalDate.now().minusYears(23));

        PhoneNumber phoneNumber=new PhoneNumber(numberDto.number,person);
        when(personRepository.findById(person.getId())).thenReturn(java.util.Optional.of(person));

        numberService.add(numberDto);

        ArgumentCaptor<PhoneNumber> phoneNumberArgumentCaptor=ArgumentCaptor.forClass(PhoneNumber.class);
        verify(numberRepository,times(1)).save(phoneNumberArgumentCaptor.capture());

        List<PhoneNumber> allCaptureValues = phoneNumberArgumentCaptor.getAllValues();

        assertEquals(1,allCaptureValues.size());
        assertEquals(numberDto.personId,allCaptureValues.get(0).getPerson().getId());
        assertEquals(numberDto.number,allCaptureValues.get(0).getNumber());

    }

    @Test
    void test_edit_should_edit_number(){
        NumberDto numberDto=new NumberDto(1,"number1",2);
        Person person=new Person("Vasya","Pupkin", LocalDate.now().minusYears(23));

        PhoneNumber phoneNumberFromDB=new PhoneNumber("number2",person);
        when(numberRepository.findById(numberDto.id)).thenReturn(java.util.Optional.of(phoneNumberFromDB));

        numberService.edit(numberDto);

        verify(numberRepository,times(1)).save(phoneNumberFromDB);

        verify(numberRepository,times(1)).save(argThat(number->
                number.getNumber().equals(numberDto.number)));


    }

    @Test
    void test_removeById_should_delete_number_by_numberId(){
        NumberDto numberDto=new NumberDto(1,"number1",2);
        Person person=new Person(2,"Vasya","Pupkin", LocalDate.now().minusYears(23));

        numberService.removeById(numberDto.id);
        List<NumberDto> byPersonId = numberService.getByPersonId(person.getId());

        assertEquals(0,byPersonId.size());
        verify(numberRepository, times(1)).deleteById(numberDto.id);
    }

    @Test
    void test_getNumberById_should_return_numberDto_by_id(){
        NumberDto numberDto=new NumberDto(1,"number1",2);
        Person person=new Person(2,"Vasya","Pupkin", LocalDate.now().minusYears(23));

        PhoneNumber phoneNumber=new PhoneNumber(numberDto.number,person);
        when(numberRepository.findById(numberDto.id)).thenReturn(java.util.Optional.of(phoneNumber));

        NumberDto numberById = numberService.getNumberById(numberDto.id);

        assertEquals(numberDto.number,numberById.number);
        assertEquals(numberDto.personId,numberById.personId);

    }

}
