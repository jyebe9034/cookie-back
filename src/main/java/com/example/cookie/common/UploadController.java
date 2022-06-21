package com.example.cookie.common;

import com.example.cookie.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UploadController extends BaseController {

    private final S3UploadUtil s3UploadUtil;

    @PostMapping(API_PREFIX + "/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("directory") String directory,
                                                          @RequestPart(value = "file") MultipartFile multipartFile) {
        return createResponseEntity(true, s3UploadUtil.upload(directory, multipartFile));
    }
}