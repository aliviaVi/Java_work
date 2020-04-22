package person.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;
    @Setter
    private String lastName;
    @Setter
    private LocalDate birthday;

    @OneToMany(mappedBy = "person")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private List<PhoneNumber> numbers=new ArrayList<>();


    public Person(String name, String lastName, LocalDate birthday){
        this.name=name;
        this.lastName=lastName;
        this.birthday=birthday;
    }

    public List<PhoneNumber> getNumbers(){
        return Collections.unmodifiableList(numbers);
    }

   /* public void addNumber(PhoneNumber number){
        numbers.add(number);
    }*/
}
