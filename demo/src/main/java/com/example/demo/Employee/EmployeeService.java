package com.example.demo.Employee;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
       return employeeRepository.findAll();
    }

    public Employee addEmpoyee(Employee employee) {
        Optional<Employee> emp = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(emp.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        return employeeRepository.save(employee);
    }

    public void deleteAnEmployee(Long employeeId) {
        boolean isExist = employeeRepository.existsById(employeeId);
        if(!isExist){
            throw new IllegalStateException("No employee found with the id " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public Employee updateAnEmployee(Long employeeId, Employee employee) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("No employee found with the id " + employeeId));
        if(employee.getName() != null && !employee.getName().isEmpty() && !Objects.equals(emp.getName(), employee.getName())){
            emp.setName(employee.getName());
        }
        if(employee.getAge() != 0 && !Objects.equals(emp.getAge(), employee.getAge())){
            emp.setAge(employee.getAge());
        }
        if(employee.getEmail() != null && !employee.getEmail().isEmpty() && !Objects.equals(emp.getEmail(), employee.getEmail())){
            Optional<Employee> emailExist = employeeRepository.findEmployeeByEmail(employee.getEmail());
            if(emailExist.isPresent()){
                throw new IllegalStateException("Email already exists");
            }
            emp.setEmail(employee.getEmail());
        }
        return emp;
    }

}
