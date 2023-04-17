package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.services.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TourController {
    @Autowired
    private final TourService tourService;

    public TourController(TourService tourService){
        this.tourService = tourService;
    }

    // Save operation
    @PostMapping("/tour")
    public TourEntity saveTour(@Valid @RequestBody TourEntity tour)
    {
        return tourService.saveTour(tour);
    }

    // Read operation
    @GetMapping("/tours")
    public List<TourEntity> fetchTours()
    {
        return tourService.fetchTour();
    }

    // Update operation
    @PutMapping("/tour/{id}")
    public TourEntity updateTour(@RequestBody TourEntity tour,
                                       @PathVariable("id") Long tourId)
    {
        return tourService.updateTour(
                tour, tourId);
    }

    // Delete operation
    @DeleteMapping("/tour/{id}")
    public String deleteTour(@PathVariable("id") Long tourId)
    {
        tourService.deleteTour(tourId);
        return "Deleted Successfully";
    }
}
