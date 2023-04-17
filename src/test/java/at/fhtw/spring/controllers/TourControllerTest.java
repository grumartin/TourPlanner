package at.fhtw.spring.controllers;

import at.fhtw.spring.persistence.entities.TourEntity;
import at.fhtw.spring.services.TourService;
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


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = TourController.class)
@AutoConfigureMockMvc(addFilters = false)
class TourControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourService tourService;

    @Autowired
    private ObjectMapper objectMapper;

    private TourEntity tour;

    @BeforeEach
    public void init(){
        tour = TourEntity
                .builder()
                .name("Bergtour")
                .description("schwere tour")
                .from("Leogang")
                .to("Saalfelden")
                .build();
    }

    @Test
    void TourControllerCreateTour() throws Exception{
        //given
        when(tourService.saveTour(tour)).thenReturn(tour);
        //when
        ResultActions response = mockMvc.perform(post("/tour")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tour)));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(tour.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(tour.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.from", CoreMatchers.is(tour.getFrom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.to", CoreMatchers.is(tour.getTo())));
    }

    @Test
    void TourControllerFetchTours() throws Exception {
        //given
        when(tourService.fetchTour()).thenReturn(List.of(tour));
        //when
        ResultActions response = mockMvc.perform(get("/tours"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(tour.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", CoreMatchers.is(tour.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].from", CoreMatchers.is(tour.getFrom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].to", CoreMatchers.is(tour.getTo())));
    }

    @Test
    void TourControllerUpdateTour() throws Exception {
        //given
        Long tourId = 12L;
        when(tourService.updateTour(tour, tourId)).thenReturn(tour);
        //when
        ResultActions response = mockMvc.perform(put("/tour/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tour)));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(tour.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(tour.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.from", CoreMatchers.is(tour.getFrom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.to", CoreMatchers.is(tour.getTo())));
    }

    @Test
    void TourControllerDeleteTour() throws Exception{
        //given
        Long tourId = 12L;
        //when
        ResultActions response = mockMvc.perform(delete("/tour/12"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk());

        ArgumentCaptor<Long> tourIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(tourService).deleteTour(tourIdCaptor.capture());

        assertThat(tourIdCaptor.getValue()).isEqualTo(tourId);
    }
}