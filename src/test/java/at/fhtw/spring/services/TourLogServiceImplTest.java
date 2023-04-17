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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TourLogServiceImplTest {
    @Mock
    private TourLogRepository tourLogRepository;
    @Mock
    private TourEntity tourEntity;
    private TourLogService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TourLogServiceImpl(tourLogRepository);
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
        //when
        underTest.saveTourLog(tourLog);
        //then
        ArgumentCaptor<TourLogEntity> tourLogEntityArgumentCaptor = ArgumentCaptor.forClass(TourLogEntity.class);
        verify(tourLogRepository).save(tourLogEntityArgumentCaptor.capture());

        TourLogEntity capturedTourLog = tourLogEntityArgumentCaptor.getValue();
        assertThat(capturedTourLog).isEqualTo(tourLog);
    }

    @Test
    void canFetchTourLogs() {
        //when
        underTest.fetchTourLogs();
        //then
        verify(tourLogRepository).findAll();
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
        //when
        underTest.deleteTourLog(tourLogIdToDelete);
        //then
        ArgumentCaptor<Long> tourLogIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(tourLogRepository).deleteById(tourLogIdCaptor.capture());

        Long capturedId = tourLogIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(tourLogIdToDelete);
    }
}