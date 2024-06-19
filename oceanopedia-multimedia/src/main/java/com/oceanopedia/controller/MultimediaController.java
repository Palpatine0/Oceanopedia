package com.oceanopedia.controller;


import com.oceanopedia.service.MultimediaService;
import com.oceanopedia.vo.OceanopediaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/multimedia")
public class MultimediaController {
    @Autowired
    private MultimediaService multimediaService;

    @GetMapping("/getBanners")
    public OceanopediaResult getBanners() {
        return multimediaService.getBanners();
    }


    @PostMapping("/uploadImage")
    public OceanopediaResult uploadImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return multimediaService.uploadImage(bytes, file.getOriginalFilename());
    }

    @PostMapping("/uploadImageNoPrefix")
    public OceanopediaResult uploadImageNoPrefix(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            return multimediaService.uploadImageNoPrefix(bytes, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            OceanopediaResult error = new OceanopediaResult();
            error.setStatus(500);
            error.setMsg("Failed to upload file due to an error: " + e.getMessage());
            return error;
        }
    }

    @DeleteMapping("/delete")
    public OceanopediaResult delete(String filePath) {
        return multimediaService.delete(filePath);
    }
}
