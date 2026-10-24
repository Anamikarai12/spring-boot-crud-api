package com.example.employee_crud_api.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.employee_crud_api.entity.Employee;
import com.example.employee_crud_api.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    // READ ALL
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    // READ BY ID
    public Optional<Employee> getEmployeeById(Long id) {

        return employeeRepository.findById(id);
    }

    // UPDATE
    public Employee updateEmployee(Long id, Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + id));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());

        return employeeRepository.save(employee);
    }
    
    //partial update
    public Employee updateEmployeePartially(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }

        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }


        if (employee.getSalary() != null) {
            existingEmployee.setSalary(employee.getSalary());
        }
        
        return employeeRepository.save(existingEmployee);
    }

    // DELETE
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}