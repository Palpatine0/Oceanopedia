package com.oceanopedia.service;


import com.oceanopedia.vo.BaseResult;

import java.io.IOException;

public interface MultimediaService {
    BaseResult uploadBanner(byte[] fileBytes, String fileName) throws IOException;

    BaseResult getBanners();

    BaseResult updateBanners(String banner1, String banner2, String banner3);

    BaseResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    BaseResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    BaseResult delete(String filePath);
}
