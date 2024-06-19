package com.oceanopedia.service.impl;

import com.oceanopedia.dao.MultimediaDao;
import com.oceanopedia.entity.Banner;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.oceanopedia.entity.Image;
import com.oceanopedia.service.MultimediaService;
import com.oceanopedia.vo.OceanopediaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {
    @Autowired
    private MultimediaDao multimediaDao;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    // the IP address info that needs to append in blank image routes
    @Value("${oceanopedia.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public OceanopediaResult getBanners() {
        OceanopediaResult result = new OceanopediaResult();
        try {
            // S1: get data
            Query query = new Query();
            query.with(PageRequest.of(0, 4));
            List<Banner> files = multimediaDao.findBanners(query);

            // S2: append url to data's front, since it hasn't.and put them to a list
            ArrayList<Object> imgRes = new ArrayList<>();
            for (Banner file : files) {
                imgRes.add(file.getUrl());
            }

            // S3: feedback
            result.setResults(imgRes);
            result.setStatus(200);

        } catch (Exception e) {
            // failure handling
            e.printStackTrace();
            result.setStatus(500);
            result.setMsg("Failed to get banner");
        }
        return result;
    }

    @Override
    public OceanopediaResult uploadImage(byte[] fileBytes, String fileName) throws IOException {
        if (fileBytes.length != 0) {
            try {
                //1.convert the byte array in to input stream
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                //2.capture the substring of the file
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //3.upload
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
                String fullPath = storePath.getFullPath();
                // 4.capture the img route and return
                String imageUrl = nginxPrefix + fullPath;
                Image image = new Image();
                image.setUrl(fullPath);
                multimediaDao.saveImage(image);
                return OceanopediaResult.ok(imageUrl);
            } catch (IOException ioException) {
                OceanopediaResult error = OceanopediaResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            OceanopediaResult error = OceanopediaResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public OceanopediaResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException {
        if (fileBytes.length != 0) {
            try {
                //1.convert the byte array in to input stream
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                //2.capture the substring of the file
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //3.upload
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
                String fullPath = storePath.getFullPath();
                // 4.capture the img route and return
                String imageUrl = fullPath;
                Image image = new Image();
                image.setUrl(fullPath);
                multimediaDao.saveImage(image);
                OceanopediaResult ok = OceanopediaResult.ok(imageUrl);
                ok.setMsg("File uploaded success");
                ok.setData(imageUrl);
                return ok;
            } catch (IOException ioException) {
                OceanopediaResult error = OceanopediaResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            OceanopediaResult error = OceanopediaResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public OceanopediaResult delete(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
        return OceanopediaResult.ok();
    }
}
