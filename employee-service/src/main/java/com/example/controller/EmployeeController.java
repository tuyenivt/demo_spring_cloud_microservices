package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@RefreshScope
@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${custom.message}")
    private String customMessage;

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        LOGGER.info("Employee findAll");
        return repository.findAll();
    }

    @GetMapping("/custom-message")
    public String customMessage() {
        LOGGER.info("custom message: {}", this.getCustomMessage());
        return this.getCustomMessage();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
