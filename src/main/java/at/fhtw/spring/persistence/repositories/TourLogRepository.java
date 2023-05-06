package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {
    @Query("SELECT t FROM tour_log t WHERE t.tour = ?1")
    List<TourLogEntity> findAllByTour(TourEntity tour);
}
