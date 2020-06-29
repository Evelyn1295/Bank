package com.ironhack.Bank.controller.impl;

import com.ironhack.Bank.controller.interfaces.SavingsController;
import com.ironhack.Bank.model.Savings;
import com.ironhack.Bank.service.model.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SavingControllerImpl implements SavingsController {

    @Autowired
    SavingService savingService;

    @GetMapping("/savings")
    public List<Savings> getAllCreditCard(){
        return savingService.findAll();
    }

    @PostMapping("/saving")
    public Savings createCreditCard(@Valid @RequestBody Savings saving){
        return savingService.save(saving);
    }

    @GetMapping("/saving/{id}")
    public ResponseEntity<Savings> getSavingsById(@PathVariable("id") Integer id){
        return savingService.getSavingsById(id);
    }

    @PutMapping("/saving/{id}")
    public ResponseEntity<Savings> updateSaving(@PathVariable("id") Integer id,@Valid @RequestBody Savings saving){
        return savingService.updateSavings(id,saving);
    }

    @DeleteMapping("/saving/{id}")
    public ResponseEntity<?> deleteSaving(@PathVariable("id") Integer id) {
        return savingService.deleteSavings(id);
    }


}
