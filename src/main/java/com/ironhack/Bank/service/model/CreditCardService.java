package com.ironhack.Bank.service.model;

import com.ironhack.Bank.model.CreditCard;
import com.ironhack.Bank.repository.model.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    public List<CreditCard> findAll(){
        return creditCardRepository.findAll();
    }

    public CreditCard save(CreditCard creditCard){
        creditCardRepository.save(creditCard);
        return creditCard;
    }

    public ResponseEntity<CreditCard> getCreditCardById(Integer id){
        return creditCardRepository.findById(id).map(creditCard -> ResponseEntity.ok().body(creditCard)).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<CreditCard> updateCreditCard(Integer id,CreditCard creditCard){
        return creditCardRepository.findById(id)
                .map(creditCardData -> {
                    creditCardData.setBalance(creditCard.getBalance());
                    CreditCard updatedTodo = creditCardRepository.save(creditCardData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteCreditCard(Integer id) {
        return creditCardRepository.findById(id)
                .map(creditCard -> {
                    creditCardRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
