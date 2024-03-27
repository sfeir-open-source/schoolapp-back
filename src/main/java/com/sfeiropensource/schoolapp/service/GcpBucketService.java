package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.exception.BadRequestException;
import com.sfeiropensource.schoolapp.exception.GCPFileUploadException;
import com.sfeiropensource.schoolapp.model.FileDto;
import com.sfeiropensource.schoolapp.utils.DataBucketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GcpBucketService {

    private final DataBucketUtil dataBucketUtil;

    public void uploadFiles(MultipartFile file) {

        log.info("Start file uploading service");

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new BadRequestException("Original file name is null");
        }
        Path path = new File(originalFileName).toPath();

        try {
            String contentType = Files.probeContentType(path);
            FileDto fileDto = dataBucketUtil.uploadFile(file, originalFileName, contentType);

            if (fileDto != null) {
                log.info("File uploaded successfully, file name: {} and url: {}", fileDto.getFileName(), fileDto.getFileUrl());
            }
        } catch (Exception e) {
            log.error("Error occurred while uploading. Error: ", e);
            throw new GCPFileUploadException("Error occurred while uploading");
        }

//        fileRepository.saveAll(inputFiles);
        log.info("File details successfully saved in the database");
    }
}
