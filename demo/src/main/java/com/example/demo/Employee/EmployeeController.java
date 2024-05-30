package com.example.demo.Employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<EmployeeResponse> getAllEmployees() {
        try {
            List<Employee> emp = employeeService.getEmployees();
            return new ResponseEntity<>(new EmployeeResponse(true, null, emp), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeResponse(false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> addNewEmployee(@RequestBody Employee employee) {
        try {
            Employee response = employeeService.addEmpoyee(employee);
            return new ResponseEntity<>(new EmployeeResponse(true, "Employee created successfully", List.of(response)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeResponse(false, "Employee creation failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeService.deleteAnEmployee(employeeId);
            return new ResponseEntity<>(new EmployeeResponse(true, "Employee deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeResponse(true, "Employee deletion failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateAnEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        try {
            Employee response = employeeService.updateAnEmployee(employeeId, employee);
            return new ResponseEntity<>(new EmployeeResponse(true, "Employee updated successfully", List.of(response)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeResponse(true, "Employee updation failed", null), HttpStatus.OK);
        }
    }

}
