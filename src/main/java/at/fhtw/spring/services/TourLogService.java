package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourLogEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourLogService {
    //Create
    TourLogEntity saveTourLog(TourLogEntity tourLogEntity, Long tourId);
    //Read
    List<TourLogEntity> fetchTourLogs(Long tourId);
    //Update
    TourLogEntity updateTourLog(TourLogEntity tourLogEntity, Long tourLogId);
    //Update
    void deleteTourLog(Long tourLogId);

    void saveTourLogs(List<TourLogEntity> tourLogs, Long tourId);
}
