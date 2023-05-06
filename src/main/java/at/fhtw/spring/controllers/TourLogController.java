package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.TourLogEntity;
import at.fhtw.spring.services.TourLogService;
import at.fhtw.spring.services.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourLogController {
    @Autowired
    private final TourLogService tourLogService;

    public TourLogController(TourLogService tourLogService){
        this.tourLogService = tourLogService;
    }

    // Save operation
    @PostMapping("/tour/{id}/tourlog")
    public TourLogEntity saveTourLog(@Valid @RequestBody TourLogEntity tourLog,
                                     @PathVariable("id") Long tourId)
    {
        return tourLogService.saveTourLog(tourLog, tourId);
    }

    // Read operation
    @GetMapping("/tourlogs/{id}")
    public List<TourLogEntity> fetchTourLog(@PathVariable("id") Long tourId)
    {
        return tourLogService.fetchTourLogs(tourId);
    }

    // Update operation
    @PutMapping("/tourlog/{id}")
    public TourLogEntity updateTourLog(@RequestBody TourLogEntity tourLog,
                                       @PathVariable("id") Long tourLogId)
    {
        return tourLogService.updateTourLog(
                tourLog, tourLogId);
    }

    // Delete operation
    @DeleteMapping("/tourlog/{id}")
    public String deleteTourLog(@PathVariable("id") Long tourLogId)
    {
        tourLogService.deleteTourLog(tourLogId);
        return "Deleted Successfully";
    }
}
