package com.example.demo.Employee;

import java.util.List;

public class EmployeeResponse {

    private final boolean success;  // Use final for immutability
    private final String message;
    private final List<Employee> data;

    // Constructor for "create" or "update" operations (includes employee)
    public EmployeeResponse(boolean success, String message, List<Employee> employees) {
        this.success = success;
        this.message = message;
        this.data = employees;
    }

    // Constructor for "delete" operation (excludes employee)
    public EmployeeResponse(boolean success, String message) {
        this(success, message, null);  // Reuse existing constructor
    }

    // Getters (optional, depends on your use case)
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Employee> getData() {
        return data;
    }

}
