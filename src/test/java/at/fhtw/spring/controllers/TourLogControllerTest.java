package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.TourLogEntity;
import at.fhtw.spring.services.TourLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TourLogController.class)
@AutoConfigureMockMvc(addFilters = false)
class TourLogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourLogService tourLogService;

    @Autowired
    private ObjectMapper objectMapper;

    private TourLogEntity tourLog;

    @BeforeEach
    public void init(){
        tourLog = TourLogEntity
                .builder()
                .comment("first log")
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .difficulty(12)
                .build();
    }

    @Test
    void TourLogControllerSaveTourLog() throws Exception{
        //given
        when(tourLogService.saveTourLog(tourLog, 12L)).thenReturn(tourLog);
        //when
        ResultActions response = mockMvc.perform(post("/tour/12/tourlog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tourLog)));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment", CoreMatchers.is(tourLog.getComment())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.difficulty", CoreMatchers.is(tourLog.getDifficulty())));
    }

    @Test
    void TourLogControllerFetchTourLog() throws Exception{
        //given
        when(tourLogService.fetchTourLogs(12L)).thenReturn(List.of(tourLog));
        //when
        ResultActions response = mockMvc.perform(get("/tourlogs/12"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment", CoreMatchers.is(tourLog.getComment())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].difficulty", CoreMatchers.is(tourLog.getDifficulty())));
    }

    @Test
    void TourLogControllerUpdateTourLog() throws Exception{
        //given
        Long tourLogId = 12L;
        when(tourLogService.updateTourLog(tourLog, tourLogId)).thenReturn(tourLog);
        //when
        ResultActions response = mockMvc.perform(put("/tourlog/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tourLog)));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment", CoreMatchers.is(tourLog.getComment())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.difficulty", CoreMatchers.is(tourLog.getDifficulty())));
    }

    @Test
    void TourLogControllerDeleteTourLog() throws Exception{
        //given
        Long tourLogId = 12L;
        //when
        ResultActions response = mockMvc.perform(delete("/tourlog/12"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk());

        ArgumentCaptor<Long> tourLogIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(tourLogService).deleteTourLog(tourLogIdCaptor.capture());

        assertThat(tourLogIdCaptor.getValue()).isEqualTo(tourLogId);
    }
}