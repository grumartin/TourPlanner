package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {
}
