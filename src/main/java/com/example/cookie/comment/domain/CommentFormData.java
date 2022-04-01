package com.example.cookie.comment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommentFormData extends Comment {

    MultipartFile image;

}
