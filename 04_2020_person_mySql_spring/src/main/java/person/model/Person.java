package person.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String lastName;
    private LocalDate birthday;

    @OneToMany(mappedBy = "person")
    private List<PhoneNumber> numbers=new ArrayList<>();

    public Person() {
    }

    public Person(String name, String lastName, LocalDate bithday){
        this.name=name;
        this.lastName=lastName;
        this.birthday=bithday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PhoneNumber> getNumbers(){
        return Collections.unmodifiableList(numbers);
    }

    public void addNumber(PhoneNumber number){
        numbers.add(number);
    }
}
