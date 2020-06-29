package com.ironhack.Bank.controller.impl;

import com.ironhack.Bank.controller.interfaces.StudentCheckingController;
import com.ironhack.Bank.model.StudentChecking;
import com.ironhack.Bank.service.model.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {

    @Autowired
    StudentCheckingService studentCheckingService;

    @GetMapping("/studentcheckings")
    public List<StudentChecking> getAllStudentChecking(){
        return studentCheckingService.findAll();
    }

    @PostMapping("/studentchecking")
    public StudentChecking createStudentChecking(@Valid @RequestBody StudentChecking studentchecking){
        return studentCheckingService.save(studentchecking);
    }

    @GetMapping("/studentchecking/{id}")
    public ResponseEntity<StudentChecking> getSavingsById(@PathVariable("id") Integer id){
        return studentCheckingService.getStudentCheckingById(id);
    }

    @PutMapping("/studentchecking/{id}")
    public ResponseEntity<StudentChecking> updateStudentChecking(@PathVariable("id") Integer id,@Valid @RequestBody StudentChecking studentchecking){
        return studentCheckingService.updateStudentChecking(id,studentchecking);
    }

    @DeleteMapping("/studentchecking/{id}")
    public ResponseEntity<?> deleteStudentChecking(@PathVariable("id") Integer id) {
        return studentCheckingService.deleteStudentChecking(id);
    }

}
