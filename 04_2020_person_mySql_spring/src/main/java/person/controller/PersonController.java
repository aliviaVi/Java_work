package person.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import person.dto.NumberDto;
import person.dto.PersonDto;
import person.service.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class PersonController {

    private static final long EARLIEST_BIRTHDAY=1000;
    private PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;

    }

    @PostMapping("/person")
    public void create(@RequestBody @Valid PersonDto personDto){
        personService.add(personDto);
    }

    @PutMapping("/person")
    public void edit(@RequestBody @Valid PersonDto personDto){
        personService.edit(personDto);
    }

    @GetMapping("/person/{id}")
    public PersonDto getById(@PathVariable @Min(1) int id){
        return personService.getById(id);
    }

    @DeleteMapping("/person/{id}")
    public void removeById(@PathVariable int id){
        personService.removeById(id);
    }

    @GetMapping("/person/name/{name}")
    public List<PersonDto> getAllByName(@PathVariable String name){
        return personService.getAllByName(name);
    }

    @GetMapping("/person")
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

    @GetMapping("/person/age")
    public List<PersonDto> getAllFilteredByAge(@RequestParam(required = false,defaultValue = "0") int min,
                                               @RequestParam(required = false,defaultValue = "" + Integer.MAX_VALUE) int max){
        return personService.getAllConstrainedByAge(min,max);
    }

    @GetMapping("/person/birthday")
    public List<PersonDto> getAllFilteredByBirthday(@RequestParam(required = false)
                                                    @DateTimeFormat(pattern = "yyyy.MM.dd")
                                                    LocalDate before,
                                                    @RequestParam(required = false)
                                                    @DateTimeFormat(pattern = "yyyy.MM.dd")
                                                    LocalDate after){
        if(before==null) before=LocalDate.now();
        if(after==null) after=LocalDate.now().minusYears(EARLIEST_BIRTHDAY);

        return personService.getAllConstrainedByBirthdays(after,before);
    }

    @DeleteMapping("/person/last-name/{pattern}")
    public void removeLastNameWithPattern(@PathVariable String pattern){
        personService.removeByLastNameWithPattern(pattern);
    }

    @GetMapping("/person/{id}/numbers")
    public List<NumberDto> getAllPersonsPhoneNumbers(@PathVariable int id){
        List<NumberDto> allPersonsPhoneNumbers = personService.getAllPersonsPhoneNumbers(id);
        return allPersonsPhoneNumbers;
    }
}
