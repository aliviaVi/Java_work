package person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NumberDto {

    public int id;
    @Setter
    public String number;

    @Setter
    public int personId;

}
