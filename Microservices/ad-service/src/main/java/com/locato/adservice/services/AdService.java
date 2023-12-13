package com.locato.adservice.services;

import com.locato.adservice.dao.AdRepository;
import com.locato.adservice.dao.LocationAdRepository;
import com.locato.adservice.dao.RoommateAdRepository;
import com.locato.adservice.entities.Ad;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {
    @Autowired
    AdRepository adRepository;
    @Autowired
    LocationAdRepository locationAdRepository;
    @Autowired
    RoommateAdRepository roommateAdRepository;
    public List<Ad> getAds(){
        return adRepository.findAll();
    }

    public boolean deleteAd(String id){
        adRepository.deleteById(id);
        return true;
    }
    public Ad editAd(Ad ad){
        return adRepository.save(ad);
    }
    public Ad getAdById(String id){
        return adRepository.findById(id).get();
    }
    public List<Ad> getAdsByUserId(String userid){
        return adRepository.getAdByUserId(userid);
    }
}
