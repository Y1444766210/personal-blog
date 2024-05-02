package org.project.service.impl;

import org.project.constants.SystemConstants;
import org.project.domain.ResponseResult;
import org.project.enums.AppHttpCodeEnum;
import org.project.exception.SystemException;
import org.project.service.UploadService;
import org.project.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = null;
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //获取文件大小
        long fileSize = img.getSize();
        //对原始文件名进行判断
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        if(fileSize >= SystemConstants.SIZE_LIMIT){
            throw new SystemException(AppHttpCodeEnum.SIZE_ERROR);
        }
        try {
            url = aliOSSUtils.upload(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //将图片上传完成后的url返回，用于浏览器回显展示
        return ResponseResult.okResult(url);
    }
}
