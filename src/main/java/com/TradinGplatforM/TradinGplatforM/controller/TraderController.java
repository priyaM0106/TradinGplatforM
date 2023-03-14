package com.TradinGplatforM.TradinGplatforM.controller;

import com.TradinGplatforM.TradinGplatforM.model.Trader;
import com.TradinGplatforM.TradinGplatforM.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TraderController {
    @Autowired
    TraderRepository traderRepository;

    @PostMapping("/traders")
    public String createNewTrader(@Valid @RequestBody Trader trader){
        traderRepository.save(trader);
        return "Trader created in database";
    }

    @GetMapping("/traders")
    public ResponseEntity<List<Trader>> getAllTraders(){
        List<Trader> traderList=new ArrayList<>();
        traderRepository.findAll().forEach(traderList::add);
        return new ResponseEntity<List<Trader>>(traderList, HttpStatus.OK);
    }

    @GetMapping("/traders/{id}")
        public ResponseEntity<Trader> getTraderById(@PathVariable long id) {
        Optional<Trader> tr = traderRepository.findById(id);
        if (tr.isPresent()) {
            return new ResponseEntity<Trader>(tr.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<Trader>(HttpStatus.NOT_FOUND);
        }
    }

        @PutMapping("/traders/balance/{id}")
        public String updateTraderById(@Valid @PathVariable long id, @RequestBody Trader trader){
            Optional<Trader> trad = traderRepository.findById(id);
            if (trad.isPresent()) {
                Trader existtrader = trad.get();
                float x =(trader.getBalance());
                float y= existtrader.getBalance();
                float z=y+x;
                existtrader.setBalance(z);
                traderRepository.save(existtrader);
                return "Employee Details against Id " + id + "updated";
            }
            else
            {
                return "Trader Details does not exist for id " + id;
            }
        }



         @DeleteMapping("/traders/{id}")
         public String deleteTraderById(@PathVariable long id, @RequestBody Trader trader) {
             Optional<Trader> trad = traderRepository.findById(id);
             if (trad.isPresent()) {
                 traderRepository.deleteById(id);
                 return "Trader Deleted Successfully";
             } else {

                 return "Trader not found";
             }
         }


}



