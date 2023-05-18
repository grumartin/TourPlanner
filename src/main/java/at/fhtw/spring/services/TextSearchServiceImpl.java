package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.repositories.TextSearchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextSearchServiceImpl implements TextSearchService{
    @Autowired
    private TextSearchRepository textSearchRepository;

    public TextSearchServiceImpl(TextSearchRepository textSearchRepository) {
        this.textSearchRepository = textSearchRepository;
    }

    @Override
    @Transactional
    public List<TourEntity> findToursByString(String searchValue) {
        return textSearchRepository.findByInputString(searchValue);
    }
}
