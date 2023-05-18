package at.fhtw.spring.persistence.repositories;

import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextSearchRepository extends JpaRepository<TourEntity, Long> {
    @Procedure("full_text_search")
    List<TourEntity> findByInputString(String inputString);
}
