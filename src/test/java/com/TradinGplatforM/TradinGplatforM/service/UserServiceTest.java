package com.TradinGplatforM.TradinGplatforM.service;

import com.TradinGplatforM.TradinGplatforM.model.History;
import com.TradinGplatforM.TradinGplatforM.model.Trader;
import com.TradinGplatforM.TradinGplatforM.repository.HistoryRepository;
import com.TradinGplatforM.TradinGplatforM.repository.TraderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @InjectMocks
    private UserService service;

    @Mock
    private TraderRepository traderRepository;

    @Mock
    private HistoryRepository historyRepository;

    @Test
    public void saveTraderTest(){
        Trader trader = new Trader();
        trader.setName("Priya");
        trader.setEmail("mittal@gmail.com");
        trader.setInitialBalance(1000f);
        Mockito.when(traderRepository.save(ArgumentMatchers.any(Trader.class))).thenReturn(trader);
        String response = service.addTrader(trader);
        Assert.assertEquals("Trader created in database", response);
        Mockito.verify(traderRepository).save(ArgumentMatchers.any(Trader.class));
    }

    @Test
    public void deleteTraderTest(){
        Trader trader=new Trader();
        trader.setId(1L);
        Mockito.when(traderRepository.findById(1L)).thenReturn(Optional.of(trader));
        String response= service.deleteTrader(1L);
        Assert.assertEquals("Trader details deleted Successfully",response);
        Mockito.verify(traderRepository).deleteById(1L);
        Mockito.when(traderRepository.findById(2L)).thenReturn(Optional.empty());
        String response2=service.deleteTrader(2L);
        Assert.assertEquals("Trader not found",response2);
        Mockito.verify(traderRepository,Mockito.never()).deleteById(2L);
    }

    @Test
    public void updateEmailAddressTest() {
        Trader trader = new Trader();
        trader.setId(1L);
        trader.setEmail("mittal@gmail.com");
        Mockito.when(traderRepository.findById(1L)).thenReturn(Optional.of(trader));
        String result = service.updateEmailTrader(1L, trader);
        Assert.assertEquals("Trader email against Id 1 updated", result);
        Mockito.verify(traderRepository).save(trader);

    }

        @Test
        public void updateEmailAddressTestFail(){
        Trader trader = new Trader();
        trader.setId(1L);
        trader.setEmail("mittal@gmail.com");
        Mockito.when(traderRepository.findById(2L)).thenReturn(Optional.empty());
        String response2=service.updateEmailTrader(2L,trader);
        Assert.assertEquals("Trader does not exist for id 2",response2);
        Mockito.verify(traderRepository, Mockito.never()).save(any());
    }

    @Test
    public void updateTraderBalanceTest(){
        Trader trader =new Trader();
        trader.setId(1L);
        trader.setTotalBalance(1000f);
        Mockito.when(traderRepository.findById(1L)).thenReturn(Optional.of(trader));
        String result= service.updateTrader(1, trader);
        Assert.assertEquals("Trader balance against Id 1 updated",result);
        Mockito.verify(traderRepository).save(trader);

    }

    @Test
    public void updateTraderBalanceFailure(){
        Trader trader =new Trader();
        trader.setId(1L);
        trader.setTotalBalance(1000f);
        Mockito.when(traderRepository.findById(2L)).thenReturn(Optional.empty());
        String response2=service.updateTrader(2L,trader);
        Assert.assertEquals("Trader does not exist for id 2",response2);
        Mockito.verify(traderRepository, Mockito.never()).save(any());
    }


    @Test
    public void getTraderByIdTest() {
        Trader trader = new Trader();
        trader.setId(1L);
        trader.setEmail("priyamittal@gmail.com");
        trader.setInitialBalance(1000f);
        Mockito.when(traderRepository.findById(1L)).thenReturn(Optional.of(trader));
        String result = service.updateTrader(1, trader);
        Assert.assertEquals(trader, service.getTrader(1L));
        Mockito.verify(traderRepository).save(trader);
    }
        @Test
            public void getTraderByIdNotFoundTest() {
            Trader trader =new Trader();
            trader.setId(1L);
            trader.setEmail("priyamittal@gmail.com");
            trader.setInitialBalance(1000f);
            when(traderRepository.findById(2L)).thenReturn(Optional.empty());
            Assert.assertNull(service.getTrader(2L));
            }


    @Test
    public void getHistoryTest(){

            List<History> expectedHistories = Arrays.asList(
                    new History(1000f,1l, LocalDateTime.of(LocalDate.of(2023,03,21),LocalTime.of(17,45))),
                    new History(2000f,2l,LocalDateTime.of(LocalDate.of(2023,03,21),LocalTime.of(17,46))));
            when(historyRepository.findAll()).thenReturn(expectedHistories);
            List<History> actualHistories = service.getHistory();
            verify(historyRepository, times(1)).findAll();
            assertEquals(expectedHistories, actualHistories);
        }


    @Test
    public void getTradersAllTest(){
            List<Trader> traders = new ArrayList<>();
            traders.add(new Trader("Priya", "priyamittal930@gmail.com",1000f));
            traders.add(new Trader("Riya", "riya@gmail.com",10000f));
            when(traderRepository.findAll()).thenReturn(traders);
            List<Trader> actualTraders =service.findAllTraders();
            assertEquals(traders, actualTraders);
        }



}
