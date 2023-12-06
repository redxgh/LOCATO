package com.locato.adservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RestController
public class ImageService {
    @PostMapping("test")
    public String uploadImageToFileSystem(@RequestParam("image") MultipartFile image) {
        LocalDateTime dateTime = LocalDateTime.now();
        String fileName = getString(image, dateTime);
        try{
            String path = "A:/Integration project/LOCATO/Microservices/ad-service/src/main/resources/static/images";            image.transferTo(new File(path,fileName));
            System.out.println(path+fileName);
            return path +fileName;
        } catch (IOException e) {
            System.out.println(e.getMessage()+"\n"+e.getCause()+"\n"+ Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
    public byte[] downloadImageFromFileSystem(String path) {
        try{
                return Files.readAllBytes(new File(path).toPath());
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n"+e.getCause()+"\n"+ Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
    public boolean deleteImage(String path)  {
        try{
            Files.delete(Paths.get(path));
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
    private static String getString(MultipartFile image, LocalDateTime dateTime) {
        String year = String.valueOf(dateTime.getYear());
        String month = String.valueOf(dateTime.getMonthValue());
        String day = String.valueOf(dateTime.getDayOfMonth());
        String hour = String.valueOf(dateTime.getHour());
        String minute = String.valueOf(dateTime.getMinute());
        String second = String.valueOf(dateTime.getSecond());
        String millisecond = String.valueOf(dateTime.getNano());
        String combinedString = year + month + day + hour + minute + second + millisecond;
        return combinedString + image.getOriginalFilename();
    }
}
