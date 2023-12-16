package com.project.supermarket_be.domain.service;

import java.io.IOException;

public interface UploadImgService {
    public String uploadBase64Image(String base64Image) throws IOException;
}
