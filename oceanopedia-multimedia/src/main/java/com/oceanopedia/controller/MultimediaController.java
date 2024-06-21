package com.oceanopedia.controller;


import com.oceanopedia.service.MultimediaService;
import com.oceanopedia.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/multimedia")
public class MultimediaController {
    @Autowired
    private MultimediaService multimediaService;

    @PostMapping("/uploadBanner")
    public BaseResult uploadBanner(MultipartFile banner) throws IOException {
        byte[] bytes = banner.getBytes();
        return multimediaService.uploadBanner(bytes, banner.getOriginalFilename());
    }

    @GetMapping("/getBanners")
    public BaseResult getBanners() {
        return multimediaService.getBanners();
    }

    @PostMapping("/updateBanners")
    public BaseResult updateBanners(String banner1, String banner2, String banner3, String bannerX) {
        return multimediaService.updateBanners(bannerX, banner2, banner3);
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
