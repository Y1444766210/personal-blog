package org.project.controller;


import lombok.extern.slf4j.Slf4j;
import org.project.domain.ResponseResult;
import org.project.service.UploadService;
import org.project.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile img)  {

        return uploadService.uploadImg(img);


    }
    
}
