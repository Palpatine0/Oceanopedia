package com.oceanopedia.controller;


import com.oceanopedia.service.MultimediaService;
import com.oceanopedia.vo.BaseResult;
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
    public BaseResult getBanners() {
        return multimediaService.getBanners();
    }

    @PostMapping("/uploadBanners")
    public BaseResult getBanners(MultipartFile banner1, MultipartFile banner2, MultipartFile banner3) {
        return multimediaService.uploadBanners(banner1, banner2, banner3);
    }


    @PostMapping("/uploadImage")
    public BaseResult uploadImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return multimediaService.uploadImage(bytes, file.getOriginalFilename());
    }

    @PostMapping("/uploadImageNoPrefix")
    public BaseResult uploadImageNoPrefix(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            return multimediaService.uploadImageNoPrefix(bytes, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = new BaseResult();
            error.setStatus(500);
            error.setMsg("Failed to upload file due to an error: " + e.getMessage());
            return error;
        }
    }

    @DeleteMapping("/delete")
    public BaseResult delete(String filePath) {
        return multimediaService.delete(filePath);
    }
}
