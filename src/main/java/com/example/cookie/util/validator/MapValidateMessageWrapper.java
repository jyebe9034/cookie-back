package com.example.cookie.util.validator;

/**
 * Map Validator 오류 메세지 Wrapper 객체
 */
public class MapValidateMessageWrapper {

    // 오류 대상 인자값 이름
    private String errorTargetName;

    // 오류 메세지
    private String errorMessage;

    public MapValidateMessageWrapper(String errorTargetName, String errorMessage) {
        this.errorTargetName = errorTargetName;
        this.errorMessage = errorMessage;
    }

    public String getErrorTargetName() {
        return this.errorTargetName;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        return getErrorMessage();
    }

}
