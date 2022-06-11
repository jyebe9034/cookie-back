package com.example.cookie.common;

import com.example.cookie.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UploadController extends BaseController {

    private final S3UploadUtil s3UploadUtil;

    @PostMapping(API_PREFIX + "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("directory") String directory,
                                                   @RequestPart(value = "file") MultipartFile multipartFile) {
        return new ResponseEntity<>(s3UploadUtil.upload(directory, multipartFile), HttpStatus.OK);
    }
}