package com.demo.uploadissue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@RestController
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    private static final String DIR = "D:\\aaa_upload\\";

    @PostMapping("/upload")
    public String upload(MultipartFile file) {

        File storeFile = new File(DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename());

        LOGGER.info("receive file: {}B", file.getSize());
        LOGGER.info("storeFile.getAbsolutePath(): {}", storeFile.getAbsolutePath());
        LOGGER.info("storeFile.exists(): {}", storeFile.exists());
        LOGGER.info("storeFile.getPath(): {}", storeFile.getPath());

        try {
            // error:
            file.transferTo(storeFile);

            // success:
//            FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(storeFile.toPath()));
        } catch (Exception e) {
            LOGGER.error("", e);
            return "fail";
        }

        return "ok";
    }
}
