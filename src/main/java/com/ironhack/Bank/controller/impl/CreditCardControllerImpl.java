package com.ironhack.Bank.controller.impl;


import com.ironhack.Bank.controller.interfaces.CreditCardController;
import com.ironhack.Bank.model.CreditCard;
import com.ironhack.Bank.service.model.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreditCardControllerImpl implements CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/creditcards")
    public List<CreditCard> getAllCreditCard(){
        return creditCardService.findAll();
    }

    @PostMapping("/creditcard")
    public CreditCard createCreditCard(@Valid @RequestBody CreditCard creditcard){
        return creditCardService.save(creditcard);
    }

    @GetMapping("/creditcard/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable("id") Integer id){
        return creditCardService.getCreditCardById(id);
    }

    @PutMapping("/creditcard/{id}")
    public ResponseEntity<CreditCard> updateChecking(@PathVariable("id") Integer id,@Valid @RequestBody CreditCard creditcard){
        return creditCardService.updateCreditCard(id,creditcard);
    }

    @DeleteMapping("/creditcard/{id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("id") Integer id) {
        return creditCardService.deleteCreditCard(id);
    }
}
