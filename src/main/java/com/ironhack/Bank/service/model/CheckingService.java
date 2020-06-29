package com.ironhack.Bank.service.model;

import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.repository.model.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingService {
    @Autowired
    CheckingRepository checkingRepository;

    public List<Checking> findAll(){
        return checkingRepository.findAll();
    }

    public Checking save(Checking checking){
        checkingRepository.save(checking);
        return checking;
    }

    public ResponseEntity<Checking> getCheckingById(Integer id){
        return checkingRepository.findById(id).map(checking -> ResponseEntity.ok().body(checking)).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Checking> updateChecking(Integer id,Checking checking){
        return checkingRepository.findById(id)
                .map(checkingData -> {
                    checkingData.setBalance(checking.getBalance());
                    Checking updatedTodo = checkingRepository.save(checkingData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteChecking(Integer id) {
        return checkingRepository.findById(id)
                .map(checking -> {
                    checkingRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    /**
    public void transactionAmount(Checking accountFrom, Checking accountTo, Money amount){
        if(!accountFrom.getId().equals(accountTo) && accountFrom.getBalance().getAmount().compareTo(amount.getAmount())==1){
            accountFrom.getBalance().decreaseAmount(amount);
            accountTo.setBalance(new Money(accountTo.getBalance().increaseAmount(amount)));
            if(accountFrom.getBalance().)
        }
    }**/

}
