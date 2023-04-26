package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.DistanceEntity;
import at.fhtw.spring.persistence.entities.TourEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Service
public interface MapService {
    ResponseEntity<byte[]> fetchMap(String start, String end);

    ResponseEntity<DistanceEntity> fetchDistance(String from, String to, String routeType);
}
