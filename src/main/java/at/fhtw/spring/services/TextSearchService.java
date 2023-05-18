package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TextSearchService {
    List<TourEntity> findToursByString(String searchValue);
}
