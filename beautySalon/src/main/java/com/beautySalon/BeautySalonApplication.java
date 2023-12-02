package com.beautySalon;

import com.beautySalon.apiCalls.AppointmentCalls;
import com.beautySalon.apiCalls.EmployeeCalls;
import com.beautySalon.apiCalls.SalonCalls;
import com.beautySalon.apiCalls.StockCalls;
import com.beautySalon.entity.Appointment;
import com.beautySalon.entity.Employee;
import com.beautySalon.entity.Salon;
import com.beautySalon.entity.Stock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class BeautySalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautySalonApplication.class, args);

		System.out.println("-----------------------");


	}


}
