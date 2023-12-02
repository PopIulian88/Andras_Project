package com.beautySalon.controller;

import com.beautySalon.entity.Appointment;
import com.beautySalon.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/addAppointment")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping("/appointments")
    public List<Appointment> findAllAppointment() {
        return appointmentService.getAppointment();
    }

    @PutMapping("/appointment/update")
    public Appointment updateStock(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/appointment/delete/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
