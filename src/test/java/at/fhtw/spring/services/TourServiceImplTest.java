package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.repositories.TourRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TourServiceImplTest {
    @Mock
    private TourRepository tourRepository;
    private TourService underTest;

    @BeforeEach
    void setUp(){
        underTest = new TourServiceImpl(tourRepository);
    }

    @Test
    void canSaveTour() {
        //Given
        TourEntity tour = TourEntity
                .builder()
                .name("Bergtour")
                .description("schwere Tour")
                .time(2342)
                .build();
        //when
        underTest.saveTour(tour);
        //then
        ArgumentCaptor<TourEntity> tourEntityArgumentCaptor = ArgumentCaptor.forClass(TourEntity.class);
        verify(tourRepository).save(tourEntityArgumentCaptor.capture());

        TourEntity capturedTour = tourEntityArgumentCaptor.getValue();
        assertThat(capturedTour).isEqualTo(tour);
    }

    @Test
    void canFetchTour() {
        //when
        underTest.fetchTour();
        //then
        verify(tourRepository).findAll();
    }

    @Test
    void updateTour() {
        //given
        TourEntity tourA = TourEntity
                .builder()
                .name("Bergtour")
                .description("schwere Tour")
                .time(2342)
                .build();

        TourEntity tourB = TourEntity
                .builder()
                .name("Radtour")
                .description("leichte Tour")
                .from("Leogang")
                .time(1222)
                .build();

        Long tourToUpdateId = 12L;

        given(tourRepository.findById(tourToUpdateId)).willReturn(Optional.ofNullable(tourA));

        //when
        underTest.updateTour(tourB, tourToUpdateId);

        //then
        ArgumentCaptor<TourEntity> tourEntityArgumentCaptor = ArgumentCaptor.forClass(TourEntity.class);
        verify(tourRepository).save(tourEntityArgumentCaptor.capture());

        TourEntity capturedTour = tourEntityArgumentCaptor.getValue();
        assertThat(capturedTour).isEqualTo(tourB);
        assertThat(capturedTour.getName()).isEqualTo("Radtour");
    }

    @Test
    void canDeleteTour() {
        //given
        Long tourIdToDelete = 12L;
        TourEntity tourB = TourEntity
                .builder()
                .name("Radtour")
                .description("leichte Tour")
                .from("Leogang")
                .time(1222)
                .build();

        given(tourRepository.findById(tourIdToDelete)).willReturn(Optional.ofNullable(tourB));
        //when
        underTest.deleteTour(tourIdToDelete);
        //then
        ArgumentCaptor<Long> tourIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(tourRepository).deleteById(tourIdCaptor.capture());

        Long capturedId = tourIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(tourIdToDelete);
    }
}