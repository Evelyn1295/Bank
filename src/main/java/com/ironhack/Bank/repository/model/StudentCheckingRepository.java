package com.ironhack.Bank.repository.model;

import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking,Integer> {

    StudentChecking findByUsername(String username);
}
