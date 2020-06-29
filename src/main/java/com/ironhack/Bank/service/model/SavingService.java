package com.ironhack.Bank.service.model;

import com.ironhack.Bank.model.Savings;
import com.ironhack.Bank.repository.model.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingService {

    @Autowired
    SavingsRepository savingRepository;

    public List<Savings> findAll(){
        return savingRepository.findAll();
    }

    public Savings save(Savings saving){
        savingRepository.save(saving);
        return saving;
    }

    public ResponseEntity<Savings> getSavingsById(Integer id){
        return savingRepository.findById(id).map(saving -> ResponseEntity.ok().body(saving)).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Savings> updateSavings(Integer id,Savings saving){
        return savingRepository.findById(id)
                .map(savingData -> {
                    savingData.setBalance(saving.getBalance());
                    Savings updatedTodo = savingRepository.save(savingData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteSavings(Integer id) {
        return savingRepository.findById(id)
                .map(saving -> {
                    savingRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
