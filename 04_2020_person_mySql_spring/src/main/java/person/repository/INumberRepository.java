package person.repository;

import org.springframework.data.repository.CrudRepository;
import person.model.PhoneNumber;


public interface INumberRepository extends CrudRepository<PhoneNumber,Integer> {
}
