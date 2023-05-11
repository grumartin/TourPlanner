package at.fhtw.spring.services;

import at.fhtw.spring.exception.ApiRequestException;
import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.entities.TourLogEntity;
import at.fhtw.spring.persistence.repositories.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository){
        this.tourRepository = tourRepository;
    }

    @Override
    public TourEntity saveTour(TourEntity tourEntity) {
        return tourRepository.save(tourEntity);
    }

    @Override
    public List<TourEntity> fetchTour() {
        return (List<TourEntity>) tourRepository.findAll();
    }

    @Override
    public TourEntity updateTour(TourEntity tourEntity, Long tourId) {
        TourEntity tourNew = tourRepository.findById(tourId)
                .orElseThrow(() -> new ApiRequestException("The provided tour was not found."));

        if (Objects.nonNull(tourEntity.getName())) {
            tourNew.setName(tourEntity.getName());
        }

        if (Objects.nonNull(tourEntity.getDescription())) {
            tourNew.setDescription(tourEntity.getDescription());
        }

        if (Objects.nonNull(tourEntity.getFrom())) {
            tourNew.setFrom(tourEntity.getFrom());
        }

        if (Objects.nonNull(tourEntity.getTo())) {
            tourNew.setTo(tourEntity.getTo());
        }

        if (Objects.nonNull(tourEntity.getType())) {
            tourNew.setType(tourEntity.getType());
        }

        if (Objects.nonNull(tourEntity.getDistance())) {
            tourNew.setDistance(tourEntity.getDistance());
        }

        if (Objects.nonNull(tourEntity.getTime())) {
            tourNew.setTime(tourEntity.getTime());
        }

        if (Objects.nonNull(tourEntity.getImgPath())) {
            tourNew.setImgPath(tourEntity.getImgPath());
        }

        return tourRepository.save(tourNew);
    }

    @Override
    public void deleteTour(Long tourId) {
        tourRepository.findById(tourId)
                .orElseThrow(() -> new ApiRequestException("The provided tour was not found."));
        tourRepository.deleteById(tourId);
    }

    @Override
    public void saveTours(List<TourEntity> tours) {
        tourRepository.saveAll(tours);
    }
}
