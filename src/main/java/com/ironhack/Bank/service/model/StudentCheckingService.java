package com.ironhack.Bank.service.model;

import com.ironhack.Bank.model.StudentChecking;
import com.ironhack.Bank.repository.model.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public List<StudentChecking> findAll(){
        return studentCheckingRepository.findAll();
    }

    public StudentChecking save(StudentChecking saving){
        studentCheckingRepository.save(saving);
        return saving;
    }

    public ResponseEntity<StudentChecking> getStudentCheckingById(Integer id){
        return studentCheckingRepository.findById(id).map(saving -> ResponseEntity.ok().body(saving)).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<StudentChecking> updateStudentChecking(Integer id,StudentChecking saving){
        return studentCheckingRepository.findById(id)
                .map(savingData -> {
                    savingData.setBalance(saving.getBalance());
                    StudentChecking updatedTodo = studentCheckingRepository.save(savingData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteStudentChecking(Integer id) {
        return studentCheckingRepository.findById(id)
                .map(saving -> {
                    studentCheckingRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
}
