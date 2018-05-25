package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.DepartmentClient;
import com.example.client.EmployeeClient;
import com.example.model.Organization;
import com.example.repository.OrganizationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationRepository repository;
    @Autowired
    DepartmentClient departmentClient;
    @Autowired
    EmployeeClient employeeClient;

    @PostMapping
    public Organization add(@RequestBody Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        return repository.add(organization);
    }

    @GetMapping("/")
    public List<Organization> findAll() {
        LOGGER.info("Organization findAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        LOGGER.info("Organization findById: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/{id}/with-departments")
    @HystrixCommand(fallbackMethod = "findByIdWithDepartmentsFallback")
    public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
        LOGGER.info("Organization find with-departments: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
        return organization;
    }

    public Organization findByIdWithDepartmentsFallback(Long id) {
        LOGGER.info("(fallback) Organization find with-departments: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    @HystrixCommand(fallbackMethod = "findByOrganizationWithEmployeesFallback")
    public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find with-departments-and-employees: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }

    public Organization findByOrganizationWithEmployeesFallback(Long id) {
        LOGGER.info("(fallback) Organization find with-departments-and-employees: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/{id}/with-employees")
    @HystrixCommand(fallbackMethod = "findByIdWithEmployeesFallback")
    public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find with-employees: id={}", id);
        Organization organization = repository.findById(id);
        organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
        return organization;
    }

    public Organization findByIdWithEmployeesFallback(Long id) {
        LOGGER.info("(fallback) Organization find with-employees: id={}", id);
        return repository.findById(id);
    }
}
