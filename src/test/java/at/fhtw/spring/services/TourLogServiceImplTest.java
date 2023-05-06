package at.fhtw.spring.services;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.persistence.entities.TourLogEntity;
import at.fhtw.spring.persistence.repositories.TourLogRepository;
import at.fhtw.spring.persistence.repositories.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TourLogServiceImplTest {
    @Mock
    private TourLogRepository tourLogRepository;
    @Mock
    private TourRepository tourRepository;
    @Mock
    private TourEntity tourEntity;
    private TourLogService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TourLogServiceImpl(tourLogRepository, tourRepository);
    }

    @Test
    void canSaveTourLog() {
        //Given
        TourLogEntity tourLog = TourLogEntity
                .builder()
                .comment("first log")
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .tour(tourEntity)
                .build();

        TourEntity tour = TourEntity
                .builder()
                .id(12L)
                .name("Bergtour")
                .description("schwere Tour")
                .time(2342)
                .build();

        given(tourRepository.findById(tour.getId())).willReturn(Optional.of(tour));
        //when
        underTest.saveTourLog(tourLog, 12L);
        //then
        ArgumentCaptor<TourLogEntity> tourLogEntityArgumentCaptor = ArgumentCaptor.forClass(TourLogEntity.class);
        verify(tourLogRepository).save(tourLogEntityArgumentCaptor.capture());

        TourLogEntity capturedTourLog = tourLogEntityArgumentCaptor.getValue();
        assertThat(capturedTourLog).isEqualTo(tourLog);
    }

    @Test
    void canFetchTourLogs() {
        //Given
        TourEntity tour = TourEntity
                .builder()
                .id(12L)
                .name("Bergtour")
                .description("schwere Tour")
                .time(2342)
                .build();

        given(tourRepository.findById(tour.getId())).willReturn(Optional.of(tour));
        //when
        underTest.fetchTourLogs(tour.getId());
        //then
        verify(tourLogRepository).findAllByTour(tour);
    }

    @Test
    void canUpdateTourLog() {
        //given
        TourLogEntity tourLogA = TourLogEntity
                .builder()
                .comment("first log")
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .tour(tourEntity)
                .build();

        TourLogEntity tourLogB = TourLogEntity
                .builder()
                .comment("second log")
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .tour(tourEntity)
                .build();

        Long tourToUpdateId = 12L;

        given(tourLogRepository.findById(tourToUpdateId)).willReturn(Optional.ofNullable(tourLogA));

        //when
        underTest.updateTourLog(tourLogB, tourToUpdateId);

        //then
        ArgumentCaptor<TourLogEntity> tourLogEntityArgumentCaptor = ArgumentCaptor.forClass(TourLogEntity.class);
        verify(tourLogRepository).save(tourLogEntityArgumentCaptor.capture());

        TourLogEntity capturedTourLog = tourLogEntityArgumentCaptor.getValue();
        assertThat(capturedTourLog).isEqualTo(tourLogB);
        assertThat(capturedTourLog.getComment()).isEqualTo("second log");
    }

    @Test
    void canDeleteTourLog() {
        //given
        Long tourLogIdToDelete = 12L;
        TourLogEntity tourLogA = TourLogEntity
                .builder()
                .comment("first log")
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .tour(tourEntity)
                .build();

        given(tourLogRepository.findById(tourLogIdToDelete)).willReturn(Optional.ofNullable(tourLogA));
        //when
        underTest.deleteTourLog(tourLogIdToDelete);
        //then
        ArgumentCaptor<Long> tourLogIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(tourLogRepository).deleteById(tourLogIdCaptor.capture());

        Long capturedId = tourLogIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(tourLogIdToDelete);
    }
}