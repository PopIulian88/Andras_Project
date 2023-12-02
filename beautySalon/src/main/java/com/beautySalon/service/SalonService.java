package com.beautySalon.service;

import com.beautySalon.entity.Salon;
import com.beautySalon.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService {
    @Autowired
    private SalonRepository salonRepository;


    public Salon saveSalon(Salon salon) {
        return salonRepository.save(salon);
    }

    public List<Salon> getSalon() {
        return salonRepository.findAll();
    }

    public void deleteSalon(Long id) {

        salonRepository.deleteById(id);
    }

    public Salon updateSalon(Salon salon) {
        Salon existingSalon = salonRepository.findById(salon.getId()).orElse(null);
        existingSalon.setAddress(salon.getAddress());
        existingSalon.setFacebook(salon.getFacebook());
        existingSalon.setInstagram(salon.getInstagram());

        return salonRepository.save(existingSalon);
    }
}
