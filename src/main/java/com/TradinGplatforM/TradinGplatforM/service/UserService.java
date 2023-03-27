package com.TradinGplatforM.TradinGplatforM.service;

import com.TradinGplatforM.TradinGplatforM.model.History;
import com.TradinGplatforM.TradinGplatforM.model.Trader;
import com.TradinGplatforM.TradinGplatforM.repository.HistoryRepository;
import com.TradinGplatforM.TradinGplatforM.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    TraderRepository traderRepository;
    @Autowired
    HistoryRepository historyRepository;


    public String addTrader(Trader trader)
    {
        Trader existtrader = trader;
        existtrader.setName(existtrader.getName());
        existtrader.setEmail(existtrader.getEmail());
        existtrader.setInitialBalance(existtrader.getInitialBalance());
        traderRepository.save(existtrader);
        return "Trader created in database";
    }

    public Trader getTrader(long id) {
        Optional<Trader> trader = traderRepository.findById(id);
        if (trader.isPresent()) {
            return trader.get();
        } else {
            return null;
        }
    }

        public List<Trader> findAllTraders(){
            List<Trader> traderList=new ArrayList<>();
            traderRepository.findAll().forEach(traderList::add);
            return traderList;

        }


      public String updateTrader(long id, Trader trader) {
          Optional<Trader> traderid = traderRepository.findById(id);
          if (traderid.isPresent()) {
              Trader existtrader = traderid.get();
              float fluctuatedBalance = (trader.getTotalBalance());
              History table=new History();
              table.setTradingFluctuation(fluctuatedBalance);
              table.setTraderId(id);
              float previousBalance = existtrader.getTotalBalance();
              float totalBalance= previousBalance+ fluctuatedBalance;
              existtrader.setTotalBalance(totalBalance);
              historyRepository.save(table);
              traderRepository.save(existtrader);
              return "Trader balance against Id " + id + " updated";
          } else {
              return "Trader does not exist for id " + id;
          }

      }


    public String updateEmailTrader(long id, Trader trader) {
        Optional<Trader> traderid = traderRepository.findById(id);
        if (traderid.isPresent()) {
            Trader existtrader = traderid.get();
            String newEmail = (trader.getEmail());
            existtrader.setEmail(newEmail);
            traderRepository.save(existtrader);
            return "Trader email against Id " + id + " updated";
        } else {
            return "Trader does not exist for id " + id;
        }

    }

    public String deleteTrader(long id) {
           Optional<Trader> traderid = traderRepository.findById(id);
             if (traderid.isPresent()) {
                 traderRepository.deleteById(id);

                 return "Trader details deleted Successfully";
             } else {

                 return "Trader not found";
             }
         }


    public List<History> getHistory(){
        return historyRepository.findAll();

    }




}
