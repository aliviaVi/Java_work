package person.controller;


import org.springframework.web.bind.annotation.*;
import person.dto.NumberDto;
import person.service.NumberService;


@RestController
public class PhoneController {

    private NumberService numberService;

    public PhoneController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/number")
    public void create(@RequestBody NumberDto numberDto){
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
        NumberDto numberById = numberService.getNumberById(id);
        return numberById;
    }


}
