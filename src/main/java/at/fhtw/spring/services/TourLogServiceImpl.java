package at.fhtw.spring.services;

import at.fhtw.spring.exception.ApiRequestException;
import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.entities.TourLogEntity;
import at.fhtw.spring.persistence.repositories.TourLogRepository;
import at.fhtw.spring.persistence.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TourLogServiceImpl implements TourLogService {
    @Autowired
    private TourLogRepository tourLogRepository;

    @Autowired
    private TourRepository tourRepository;

    public TourLogServiceImpl(TourLogRepository tourLogRepository){
        this.tourLogRepository = tourLogRepository;
    }
    @Override
    public TourLogEntity saveTourLog(TourLogEntity tourLogEntity, Long tourId) {
        TourEntity tourEntity = tourRepository.findById(tourId)
                .orElseThrow(() -> new ApiRequestException("Tour with given ID does not exist."));
        tourLogEntity.setTour(tourEntity);
        return tourLogRepository.save(tourLogEntity);
    }

    @Override
    public List<TourLogEntity> fetchTourLogs() {
        return (List<TourLogEntity>) tourLogRepository.findAll();
    }

    @Override
    public TourLogEntity updateTourLog(TourLogEntity tourLogEntity, Long tourLogId) {
        TourLogEntity tourLogNew = tourLogRepository.findById(tourLogId)
                .orElseThrow(() -> new ApiRequestException("The provided tourlog was not found."));

        if (Objects.nonNull(tourLogEntity.getCreationTime())) {
            tourLogNew.setCreationTime(tourLogEntity.getCreationTime());
        }

        if (Objects.nonNull(tourLogEntity.getComment())) {
            tourLogNew.setComment(tourLogEntity.getComment());
        }

        if (Objects.nonNull(tourLogEntity.getDifficulty())) {
            tourLogNew.setDifficulty(tourLogEntity.getDifficulty());
        }

        if (Objects.nonNull(tourLogEntity.getTotalTime())) {
            tourLogNew.setTotalTime(tourLogEntity.getTotalTime());
        }

        if (Objects.nonNull(tourLogEntity.getRating())) {
            tourLogNew.setRating(tourLogEntity.getRating());
        }

        return tourLogRepository.save(tourLogNew);
    }

    @Override
    public void deleteTourLog(Long tourLogId) {
       tourLogRepository.findById(tourLogId)
                .orElseThrow(() -> new ApiRequestException("The provided tourlog was not found."));
        tourLogRepository.deleteById(tourLogId);
    }
}
