package com.example.cookie.util.message;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Message {

    /** 성공코드 */
    성공("SUCCESS", "SUCCESS", ""),

    /** 공통 오류코드 */
    시스템오류("ERROR", "알 수 없는 문제가 발생했습니다.", ""),

    /** 탈퇴 오류코드 */
    탈퇴오류("LEAVEERROR", "탈퇴 중 문제가 발생했습니다.", "");

    private String code;
    private String message;
    private String target;

    Message(String code, String message, String target) {
        this.code = code;
        this.message = message;
        this.target = target;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTarget() {
        return target;
    }
}
