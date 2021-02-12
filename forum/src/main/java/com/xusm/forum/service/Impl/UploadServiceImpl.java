package com.xusm.forum.service.Impl;

import com.xusm.forum.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    // 能够上传的文件类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/jpeg","image/gif");

    // 服务器中上传文件的存储目录（配置文件中）
    @Value("${xusm.upload.file-path}")
    private String uploadPath;
    // 返回的 url 前缀（配置文件中）
    @Value("${xusm.upload.file-url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        // 校验类型是否合法
        if(!CONTENT_TYPE.contains(file.getContentType())){
            LOGGER.info(file.getOriginalFilename() + "文件类型不合法");
            return null;
        }
        try {
            if(ImageIO.read(file.getInputStream()) == null){
                LOGGER.info(file.getOriginalFilename() + "文件内容不能为空");
                return null;
            }

            // 根据时间戳生成文件名
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String filename = simpleDateFormat.format(new Date()) + "." + StringUtils.substringAfter(file.getOriginalFilename(),".");
            // 上传文件
            file.transferTo(new File(uploadPath,filename));
            // 得到访问文件的 url
            String url = fileUrl + filename;
            return url;
        } catch (IOException e) {
            LOGGER.info("服务器错误！");
            e.printStackTrace();
        }

        return null;
    }
}
