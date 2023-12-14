package com.project.supermarket_be.domain.service;

import java.io.IOException;

public interface UploadImg {
    public String uploadBase64Image(String base64Image) throws IOException;
}
