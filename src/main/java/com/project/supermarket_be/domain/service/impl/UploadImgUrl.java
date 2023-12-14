package com.project.supermarket_be.domain.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.supermarket_be.domain.service.UploadImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadImgUrl implements UploadImg {
    private final Cloudinary cloudinary;
    @Override
    public String uploadBase64Image(String base64Image) throws IOException {
        Map uploadResult = cloudinary.uploader()
                .upload("data:image/png;base64," + base64Image, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}
