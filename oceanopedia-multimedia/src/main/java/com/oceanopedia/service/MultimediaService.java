package com.oceanopedia.service;


import com.oceanopedia.vo.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MultimediaService {
    BaseResult getBanners();

    BaseResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    BaseResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    BaseResult delete(String filePath);

    BaseResult uploadBanners(MultipartFile banner1, MultipartFile banner2, MultipartFile banner3);
}
