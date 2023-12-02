package com.beautySalon.service;

import com.beautySalon.entity.Appointment;
import com.beautySalon.entity.Employee;
import com.beautySalon.repository.AppointmentRepository;
import com.beautySalon.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setTelNo(employee.getTelNo());
        existingEmployee.setPicURL(employee.getPicURL());
        existingEmployee.setWallet(employee.getWallet());
        existingEmployee.setMassage(employee.getMassage());
        existingEmployee.setMyStock(employee.getMyStock());
        existingEmployee.setAppointmentList(employee.getAppointmentList());

        return employeeRepository.save(existingEmployee);
    }
}
