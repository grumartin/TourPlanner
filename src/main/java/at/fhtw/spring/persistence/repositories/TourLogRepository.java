package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {
}
