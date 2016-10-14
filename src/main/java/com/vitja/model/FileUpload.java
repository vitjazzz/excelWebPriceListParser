package com.vitja.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Viktor on 14.10.2016.
 */
public class FileUpload {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
