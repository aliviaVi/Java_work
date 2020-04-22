package person.mapper;

import org.springframework.stereotype.Component;
import person.dto.NumberDto;
import person.model.PhoneNumber;

@Component
public class NumberMapper {

    public NumberDto mapPhoneNumberToNumberDto(PhoneNumber phoneNumber){
        return new NumberDto(phoneNumber.getId(),phoneNumber.getNumber(),phoneNumber.getPerson().getId());
    }
}
