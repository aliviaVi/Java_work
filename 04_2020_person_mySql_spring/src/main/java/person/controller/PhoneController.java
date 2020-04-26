package person.controller;


import org.springframework.web.bind.annotation.*;
import person.dto.NumberDto;
import person.service.NumberService;

import java.util.List;




@RestController
public class PhoneController {

    private NumberService numberService;

    public PhoneController(NumberService numberService) {
        this.numberService = numberService;
    }


    @GetMapping("/person/{personId}/number")
    public List<NumberDto> getNumbersByPersonId(@PathVariable int personId){
        return numberService.getByPersonId(personId);
    }
    @PostMapping("/person/{personId}/number")
    public void create(@RequestBody NumberDto numberDto,@PathVariable int personId){
        numberDto.personId=personId;
        numberService.add(numberDto);
    }

    @PutMapping("/number")
    public void edit(@RequestBody NumberDto numberDto){
        numberService.edit(numberDto);
    }
    @DeleteMapping("/number/{id}")
    public void removeById(@PathVariable int id){
        numberService.removeById(id);
    }

    @GetMapping("/number/{id}")
    public NumberDto getNumberById(@PathVariable int id){
        return  numberService.getNumberById(id);
    }


}
