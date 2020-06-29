package com.ironhack.Bank.controller.impl;

import javax.validation.Valid;
import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.repository.model.CheckingRepository;
import com.ironhack.Bank.service.model.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CheckingControllerImpl {

    @Autowired
    CheckingService checkingService;

    @GetMapping("/checkings")
    public List<Checking> getAllChecking(){
        return checkingService.findAll();
    }

    @PostMapping("/checking")
    public Checking createChecking(@Valid @RequestBody Checking checking){
        return checkingService.save(checking);
    }

    @GetMapping("/checking/{id}")
    public ResponseEntity<Checking> getCheckingById(@PathVariable("id") Integer id){
        return checkingService.getCheckingById(id);
    }

    @PutMapping("/checking/{id}")
    public ResponseEntity<Checking> updateChecking(@PathVariable("id") Integer id,@Valid @RequestBody Checking checking){
        return checkingService.updateChecking(id,checking);
    }

    @DeleteMapping("/checking/{id}")
    public ResponseEntity<?> deleteChecking(@PathVariable("id") Integer id) {
        return checkingService.deleteChecking(id);
    }
}
