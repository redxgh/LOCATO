package com.locato.adservice.services;

import com.locato.adservice.dao.AdRepository;
import com.locato.adservice.dao.LocationAdRepository;
import com.locato.adservice.dao.RoommateAdRepository;
import com.locato.adservice.entities.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
