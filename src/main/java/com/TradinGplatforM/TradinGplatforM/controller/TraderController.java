package com.TradinGplatforM.TradinGplatforM.controller;

import com.TradinGplatforM.TradinGplatforM.model.History;
import com.TradinGplatforM.TradinGplatforM.model.Trader;
import com.TradinGplatforM.TradinGplatforM.repository.TraderRepository;
import com.TradinGplatforM.TradinGplatforM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
@ResponseBody
public class TraderController {
    @Autowired

    private UserService service;
    @PostMapping("/traders")
    public String createNewTrader(@Valid @RequestBody Trader trader){
        return service.addTrader(trader);
    }

    @GetMapping("/traders")
    public ResponseEntity<List<Trader>> getAllTraders(){
        return new ResponseEntity<List<Trader>>(service.findAllTraders(), HttpStatus.OK);

    }

    @GetMapping("/traders/{id}")
        public ResponseEntity<Trader> getTraderById(@PathVariable long id) {
        Trader trader = service.getTrader(id);
        if (null != trader) {
         //   return new ResponseEntity<Trader>(HttpStatus.FOUND);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trader);
        } else {
           // return new ResponseEntity<Trader>(HttpStatus.NO_CONTENT);
            return ResponseEntity.noContent().build();
        }
    }


        @PutMapping("/traders/balance/{id}")
        public String updateTraderById(@Valid @PathVariable long id, @RequestBody Trader trader){
           return service.updateTrader(id,trader) ;
        }

        @PutMapping("/traders/email/{id}")
        public String updateTraderByEmail(@Valid @PathVariable long id, @RequestBody Trader trader){
           return service.updateEmailTrader(id,trader) ;
    }


         @DeleteMapping("/traders/{id}")
         public String deleteTraderById(@PathVariable long id) {

             return service.deleteTrader(id);
         }


         @GetMapping("/traders/history")
         public List<History> getTraderHistory(){
               return service.getHistory();
        }



}



