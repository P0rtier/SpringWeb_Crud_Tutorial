package pl.uzi.springweb_crud_tutorial.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.uzi.springweb_crud_tutorial.model.VideoCassette;

@Repository
public interface VideoCassetteRepository extends CrudRepository<VideoCassette,Long> {
}
