package com.beautySalon.service;

import com.beautySalon.entity.Appointment;
import com.beautySalon.entity.Stock;
import com.beautySalon.repository.AppointmentRepository;
import com.beautySalon.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointment() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(Long id) {

        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getId()).orElse(null);
        existingAppointment.setFirstName(appointment.getFirstName());
        existingAppointment.setLastName(appointment.getLastName());
        existingAppointment.setEmail(appointment.getEmail());
        existingAppointment.setTelNo(appointment.getTelNo());
        existingAppointment.setDay(appointment.getDay());
        existingAppointment.setMonth(appointment.getMonth());
        existingAppointment.setYear(appointment.getYear());
        existingAppointment.setService(appointment.getService());
        existingAppointment.setMassage(appointment.getMassage());



        return appointmentRepository.save(existingAppointment);
    }
}
