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
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<? extends Ad> addAd(@RequestParam String title,
                    @RequestParam String description,
                    @RequestParam double price,
                    @RequestParam String location,
                    @RequestParam double surface,
                    @RequestParam int rooms,
                    @RequestParam int bathrooms,
                    @RequestParam int best,
                    @RequestParam ArrayList<MultipartFile> imagesArr,
                    @RequestParam String type,
                    @RequestParam String categoryId,
                    @RequestParam(value = "gender", defaultValue = "-1") int gender
                    ){
        String[] images = new String[5];
        int i = 0;
        for(MultipartFile image : imagesArr){
            images[i] = imageService.uploadImageToFileSystem(image);
            i++;
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
    @GetMapping("image/{path}")
    public ResponseEntity<?> getImage(@PathVariable("path") String path){
        byte[] image = imageService.downloadImageFromFileSystem(path);
        if (image == null) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic());
            return ResponseEntity.status(HttpStatus.SC_OK)
                    .headers(headers)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image);
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
    public ResponseEntity<?> deleteAd(@RequestParam("ad") Ad ad){
        if(ad instanceof  Roommatead){
            roommateAdRepository.delete((Roommatead) ad);
            for(String image : ad.getAccomodation().getImages()){
                imageService.deleteImage(image);
            }
        }
        else{
            locationAdRepository.delete((Locationad) ad);
            for(String image : ad.getAccomodation().getImages()){
                imageService.deleteImage(image);
            }
        }
        return ResponseEntity.status(HttpStatus.SC_OK).build();
    }
    @PatchMapping("editAd")
    public ResponseEntity<Ad> editAd(@RequestParam("ad")Ad ad){
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(A);
    }
    @GetMapping("categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(categoryService.getCategories());
    }
}
