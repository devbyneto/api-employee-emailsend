package com.example.employee.controller;

import com.example.employee.domain.EmailModel;
import com.example.employee.domain.EmployeeDto;
import com.example.employee.domain.EmployeeModel;
import com.example.employee.domain.TypeEmailEnum;
import com.example.employee.service.EmailService;
import com.example.employee.service.EmployeeService;
import com.sun.net.httpserver.HttpsServer;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmailService emailService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEmployee(@PathVariable(value="id") UUID id){
        Optional<EmployeeModel> employeeOpt = employeeService.findById(id);
        if(employeeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeOpt.get());
    }

    @GetMapping
    public List<EmployeeModel> getAllEmployees(){
        return employeeService.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeDto, employeeModel);
        EmailModel emailModel = new EmailModel(employeeModel.getEmail(), TypeEmailEnum.ADMISSION);
        emailService.sendEmail(emailService.emailBuilder(emailModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value="id") UUID id){
        Optional<EmployeeModel> employeeOpt = employeeService.findById(id);
        if (employeeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        EmailModel emailModel = new EmailModel(employeeOpt.get().getEmail(), TypeEmailEnum.RESIGNATION);
        emailService.sendEmail(emailService.emailBuilder(emailModel));
        employeeService.delete(employeeOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(value="id") UUID id, @RequestBody @Valid EmployeeDto employeeDto){
        Optional<EmployeeModel> employeeOpt = employeeService.findById(id);
        if(employeeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        var employeeModel = employeeOpt.get();
        employeeModel.setCpf(employeeDto.cpf());
        employeeModel.setEmail(employeeDto.email());
        employeeModel.setName(employeeDto.name());
        employeeModel.setSalary(employeeDto.salary());

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employeeModel));
    }

}
