package com.TradinGplatforM.TradinGplatforM.controller;

import com.TradinGplatforM.TradinGplatforM.model.History;
import com.TradinGplatforM.TradinGplatforM.model.Trader;
import com.TradinGplatforM.TradinGplatforM.repository.HistoryRepository;
import com.TradinGplatforM.TradinGplatforM.repository.TraderRepository;
import com.TradinGplatforM.TradinGplatforM.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TraderControllerTest {

    @InjectMocks
    TraderController controller;
    @Mock
    TraderRepository traderRepository;

    @Mock
    HistoryRepository historyRepository;

    @MockBean
    UserService service;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void createNewTraderTest() throws Exception {
        Trader trader = new Trader("Priya", "mittal@gmail.com", 1000f);
        mockMvc.perform((RequestBuilder) post("http://localhost:8080/api/traders").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(trader)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllTradersTest() throws Exception {
        List<Trader> trader = Arrays.asList(new Trader("Priya", "priyamittal930@gmail.com",1000f));
        Mockito.when(service.findAllTraders()).thenReturn(trader);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/traders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(trader.size()));

    }



    @Test
    public void deleteByIdTest() throws Exception {
        long id = 1L;

        doNothing().when(traderRepository).deleteById(id);
        mockMvc.perform(delete("http://localhost:8080/api/traders/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getTraderHistoryTest() throws Exception {
        List<History> histories = Arrays.asList(new History(1000f, 1l, LocalDateTime.of(LocalDate.of(2023, 03, 21), LocalTime.of(17, 45))));
        Mockito.when(service.getHistory()).thenReturn(histories);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/traders/history")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(histories.size()))
               // .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradingFluctuation").value(histories.get(0).getTradingFluctuation()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].traderId").value(histories.get(0).getTraderId()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].updatedOn").value(histories.get(0).getUpdatedOn()));
               .andDo(print());
    }


    @Test
    public void updateTraderByIdTest() throws Exception {
        long traderId = 1L;
        Trader trader = new Trader("Priya", "priyamittal930@gmail.com", 1000f);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/traders/balance/{id}", traderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        // .content("{\"name\":\"Priya\",\"email\":\"priya930@gmail.com\",\"initialBalance\":1000f}"))
                        .content("{\"initialBalance\":\"2000f\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }


    @Test
    public void updateTraderByEmailTest() throws Exception {
        long id = 1L;
        Trader trader = new Trader("Priya", "priyamittal930@gmail.com", 1000f);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/traders/email/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"priya@gmail.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());


    }


    @Test
    public void getTraderByIdTest() throws Exception {
        long id = 1L;
        Trader trader = new Trader("Priya", "priyamittal930@gmail.com", 1000f);
        when(service.getTrader(id)).thenReturn(trader);
        mockMvc.perform(get("http://localhost:8080/api/traders/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Priya")))
                .andExpect(jsonPath("$.email", is("priyamittal930@gmail.com")))
               .andExpect(jsonPath("$.initialBalance", is(1000.0)))
               .andDo(print());
    }






}































