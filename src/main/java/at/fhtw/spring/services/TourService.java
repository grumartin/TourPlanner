package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourService {
    //Create
    TourEntity saveTour(TourEntity tourEntity);
    //Read
    List<TourEntity> fetchTour();
    //Update
    TourEntity updateTour(TourEntity tourEntity, Long tourId);
    //Update
    void deleteTour(Long tourId);

}
