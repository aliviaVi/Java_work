package person.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import person.model.PhoneNumber;

import java.util.List;


public interface INumberRepository extends CrudRepository<PhoneNumber,Integer> {

    @Query("select num from PhoneNumber num where num.person.id=?1")
    public List<PhoneNumber> findByPersonId(@Param("id") int id);

}
