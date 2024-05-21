package com.example.demo.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity
public class Employee {
    
    @Id
    @SequenceGenerator(
        initialValue=1000,
        sequenceName="employee_sequence",
        name="employee_sequence",
        allocationSize=1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE,
        generator="employee_sequence"
    )
    
    private Long employeeId;
    private String name;
    private String email;
    private int age;

    public Employee(int age, String email, Long employeeId, String name) {
        this.age = age;
        this.email = email;
        this.employeeId = employeeId;
        this.name = name;
    }

    public Employee(int age, String email, String name) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    


}
