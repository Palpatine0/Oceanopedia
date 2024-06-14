package com.oceanopedia.service;


import com.oceanopedia.vo.OceanopediaResult;

import java.io.IOException;

public interface MultimediaService {
    OceanopediaResult getBanners();

    OceanopediaResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    OceanopediaResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    OceanopediaResult delete(String filePath);
}
