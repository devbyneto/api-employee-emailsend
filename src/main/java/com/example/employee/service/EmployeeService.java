package com.example.employee.service;

import com.example.employee.domain.EmployeeModel;
import com.example.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeModel save(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    public List<EmployeeModel> getAll(){
        return employeeRepository.findAll();
    }

    public Optional<EmployeeModel> findById(UUID id){
        return employeeRepository.findById(id);
    }

    @Transactional
    public void delete(EmployeeModel employeeModel){
        employeeRepository.delete(employeeModel);
    }
}
