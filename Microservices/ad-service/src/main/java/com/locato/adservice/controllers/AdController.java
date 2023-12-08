package com.locato.adservice.controllers;
import com.locato.adservice.dao.LocationAdRepository;
import com.locato.adservice.dao.RoommateAdRepository;
import com.locato.adservice.entities.*;
import com.locato.adservice.services.AdService;
import com.locato.adservice.services.CategoryService;
import com.locato.adservice.services.ImageService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
@CrossOrigin("*")
@RestController
public class AdController {
    @Autowired
    AdService adService;
    @Autowired
    ImageService imageService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    LocationAdRepository locationAdRepository;
    @Autowired
    RoommateAdRepository roommateAdRepository;
    @GetMapping("/getAds")
    public List<Ad> getAds(){
        return adService.getAds();
    }

    @PostMapping("/addAd")
    public ResponseEntity<? extends Ad> addAd(
                    @RequestParam String title,
                    @RequestParam String description,
                    @RequestParam double price,
                    @RequestParam String location,
                    @RequestParam double surface,
                    @RequestParam int rooms,
                    @RequestParam int bathrooms,
                    @RequestParam int best,
                    @RequestParam("imagesArr") MultipartFile[] imagesArr,
                    @RequestParam String type,
                    @RequestParam String categoryId,
                    @RequestParam(value = "gender", defaultValue = "-1") int gender
                    ){
        ArrayList<String> images = new ArrayList<>();
        for(MultipartFile image : imagesArr){
            images.add(imageService.uploadImageToFileSystem(image));
        }
        if(gender == -1){
            Locationad ad = new Locationad(null,title,description,price, LocalTime.now(),new Accomodation(location,surface,rooms,bathrooms,best,images,type,categoryService.getCategoryById(categoryId)));
            return ResponseEntity.status(HttpStatus.SC_OK).body(locationAdRepository.save(ad));
        }
        else{
            Roommatead ad = new Roommatead(null,title,description,price, LocalTime.now(),new Accomodation(location,surface,rooms,bathrooms,best,images,type,categoryService.getCategoryById(categoryId)),gender);
            return ResponseEntity.status(HttpStatus.SC_OK).body(roommateAdRepository.save(ad));
        }

    }

    @PutMapping("addCategory")
    public Category addCategory(@RequestParam("image")MultipartFile image,
                                         @RequestParam("name") String name
                                         ){
        Category category = new Category(name,imageService.uploadImageToFileSystem(image));
        return categoryService.addCategory(category);

    }
    @DeleteMapping("deleteAd")
    public ResponseEntity<?> deleteAd(@RequestParam("id")String id){
        return ResponseEntity.status(HttpStatus.SC_OK).body(adService.deleteAd(id));
    }
    @PatchMapping("editAd")
    public ResponseEntity<Ad> editAd(
            @RequestParam String id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String location,
            @RequestParam double surface,
            @RequestParam int rooms,
            @RequestParam int bathrooms,
            @RequestParam int best,
            @RequestParam("imagesArr") MultipartFile[] imagesArr,
            @RequestParam String type,
            @RequestParam String categoryId,
            @RequestParam(value = "gender", defaultValue = "-1") int gender
    ){
        /*ArrayList<String> images = new ArrayList<>();
        for(MultipartFile image : imagesArr){
            images.add(imageService.uploadImageToFileSystem(image));
        }*/
        if(gender == -1){
            Locationad ad = new Locationad(id,title,description,price, LocalTime.now(),new Accomodation(location,surface,rooms,bathrooms,best,null,type,categoryService.getCategoryById(categoryId)));
            return ResponseEntity.status(HttpStatus.SC_OK).body(locationAdRepository.save(ad));
        }
        else{
            Roommatead ad = new Roommatead(id,title,description,price, LocalTime.now(),new Accomodation(location,surface,rooms,bathrooms,best,null,type,categoryService.getCategoryById(categoryId)),gender);
            return ResponseEntity.status(HttpStatus.SC_OK).body(roommateAdRepository.save(ad));
        }
    }
    @GetMapping("categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(categoryService.getCategories());
    }
    @GetMapping("/ads/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(adService.getAdById(id));
    }
}
