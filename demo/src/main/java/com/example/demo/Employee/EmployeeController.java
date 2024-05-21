package com.example.demo.Employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin(origins="*")
public class EmployeeController {

    private final EmployeeService employeeService;
        
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {        
        try {
            Employee response = employeeService.addEmpoyee(employee);
             return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {        
        try {
            employeeService.deleteAnEmployee(employeeId);
             return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateAnEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        try {
            Employee response = employeeService.updateAnEmployee(employeeId,employee);
             return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
}
