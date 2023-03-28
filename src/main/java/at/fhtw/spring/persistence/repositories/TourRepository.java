package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
