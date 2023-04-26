package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.DistanceEntity;
import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
    @Autowired
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping(value = "/map", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> fetchMap(@RequestParam("start") String start,
                                               @RequestParam("end") String end)
    {
        return mapService.fetchMap(start, end);
    }

    @GetMapping("/distance")
    public ResponseEntity<DistanceEntity> fetchDistance(@RequestParam("from") String from,
                                                   @RequestParam("to") String to,
                                                   @RequestParam("routeType") String routeType)
    {
        return mapService.fetchDistance(from, to, routeType);
    }
}
