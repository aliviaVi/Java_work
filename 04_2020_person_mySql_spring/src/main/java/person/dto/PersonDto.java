package person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import person.validation.annotation.FullName;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FullName(10)
@NoArgsConstructor
@Getter
public class PersonDto {

    @Min(0)
    public int id;

    @Setter
    @Size(max=10,min=1, message = "The name '${validatedValue}' is shorter than {min} or longer than {max}")
    public String firstName;

    @Setter
    @Size(min=2, max=20)
    public String lastName;

    @Setter
    @JsonFormat(pattern = "yyyy.MM.dd")
    public LocalDate birthday;

    @Setter
    public List<NumberDto> numbers=new ArrayList<>();

    public PersonDto( int id, String firstName, String lastName, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
