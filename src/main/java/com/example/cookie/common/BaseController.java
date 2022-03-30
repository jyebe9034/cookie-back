package com.example.cookie.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    /**
     * @Method Name : createResponseEntity
     * @Method 설명  : 입력받은 성공여부에 따라 Status가 다른 ResponseEntity 생성
     * @Date : 2021. 01. 25
     * @Author : jh. lim.
     * @param isSuccess
     * @param body
     * @param headers
     * @return
     */
    protected ResponseEntity createResponseEntity(boolean isSuccess, Object body, HttpHeaders headers) {
        if (isSuccess == false) {
            body = null;
            headers = null;
        }
        return ResponseEntity.status(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST).headers(headers).body(body);
    }

    protected ResponseEntity createResponseEntity(boolean isSuccess, Object body) {
        return createResponseEntity(isSuccess, body, null);
    }

    /**
     * @Method Name : getPageRequest
     * @Method 설명  : PageRequest 오름차순 정렬
     * @Date : 2021. 01. 25
     * @Author : jh. lim.
     * @param page, size, sort
     * @return : PageRequest
     */
    protected PageRequest getPageRequest(int page, int size, String sort) {
        return PageRequest.of(page-1, size, Sort.Direction.DESC, sort);
    }


    /**
     * @Method Name : getPageRequestAsc
     * @Method 설명  : PageRequest Asc 내림차순 정렬
     * @Date : 2021. 01. 25
     * @Author : jh. lim.
     * @param page, size, sort
     * @return : PageRequest
     */
    protected PageRequest getPageRequestAsc(int page, int size, String sort) {
        return PageRequest.of(page-1, size, Sort.Direction.ASC, sort);
    }
}
