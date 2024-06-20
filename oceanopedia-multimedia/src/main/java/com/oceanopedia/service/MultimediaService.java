package com.oceanopedia.service;


import com.oceanopedia.vo.BaseResult;

import java.io.IOException;

public interface MultimediaService {
    BaseResult getBanners();

    BaseResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    BaseResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    BaseResult delete(String filePath);
}
