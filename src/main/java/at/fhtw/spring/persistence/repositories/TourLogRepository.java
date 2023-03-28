package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourLogRepository extends CrudRepository<TourLogEntity, Long> {
}
