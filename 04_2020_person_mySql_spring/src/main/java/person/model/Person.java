package person.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<PhoneNumber> numbers=new ArrayList<>();


    public Person(String name, String lastName, LocalDate birthday){
        this.name=name;
        this.lastName=lastName;
        this.birthday=birthday;
    }
    public Person(int id, String name, String lastName, LocalDate birthday){
        this.id=id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        return numbers != null ? numbers.equals(person.numbers) : person.numbers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (numbers != null ? numbers.hashCode() : 0);
        return result;
    }
}
