package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.services.TextSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private TextSearchService textSearchService;

    public SearchController(TextSearchService textSearchService) {
        this.textSearchService = textSearchService;
    }

    @GetMapping("/full_text_search/{value}")
    public List<TourEntity> findTours(@PathVariable("value") String value)
    {
        return textSearchService.findToursByString(value);
    }
}
