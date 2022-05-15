package com.example.cookie.util.validator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Map Validator 결과 객체
 */
public class MapValidateResult {

    /** 에러 메세지 목록 */
    private Set<com.example.cookie.util.validator.MapValidateMessageWrapper> errorMessages = new LinkedHashSet<>();

    /**
     * 에러 메세지 추가
     * @param targetName
     * @param errorMessage
     */
    protected void addErrorMessage(String targetName, String errorMessage) {
        errorMessages.add(new com.example.cookie.util.validator.MapValidateMessageWrapper(targetName, errorMessage));
    }

    /**
     * 에러 대상명 목록 가져오기
     * @return
     */
    public Set<String> getErrorTargetNames() {
        return this.errorMessages.stream().map(mapValidateMessageWrapper -> mapValidateMessageWrapper.getErrorTargetName()).collect(Collectors.toSet());
    }

    /**
     * 에러 대상명 목록 중 첫번째 항목 가져오기
     * @return
     */
    public String getErrorTargetName() {
        if(!this.isValid()) {
            return this.errorMessages.iterator().next().getErrorTargetName();
        } else {
            return null;
        }
    }

    /**
     * 에러 메세지 목록 가져오기
     * @return
     */
    public Set<String> getErrorMessages() {
        return this.errorMessages.stream().map(mapValidateMessageWrapper -> mapValidateMessageWrapper.getErrorMessage()).collect(Collectors.toSet());
    }

    /**
     * 에러 메세지 목록 중 첫번째 항목 가져오기
     * @return
     */
    public String getErrorMessage() {
        if(!this.isValid()) {
            return this.errorMessages.iterator().next().toString();
        } else {
            return null;
        }
    }

    /**
     * 유효성 검사 성공 여부
     * @return
     */
    public boolean isValid() {
        return errorMessages.size() == 0;
    }

}
