package com.beautySalon.controller;

import com.beautySalon.entity.Salon;
import com.beautySalon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalonController {
    @Autowired
    private SalonService salonService;

    @PostMapping("/addSalon")
    public Salon addSalon(@RequestBody Salon salon){
        return salonService.saveSalon(salon);
    }

    @GetMapping("/salons")
    public List<Salon> findAllSalon() {
        return salonService.getSalon();
    }

    @PutMapping("/salon/update")
    public Salon updateSalon(@RequestBody Salon salon){
        return salonService.updateSalon(salon);
    }

    @DeleteMapping("/salon/delete/{id}")
    public void deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
    }
}
