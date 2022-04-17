package com.example.cookie.util.validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Map Validator 규칙 객체
 */
public class MapValidateRule implements Cloneable {

    /** Map 객체에서 검사할 key */
    private String targetName;
    /** Map 객체에서 검사할 key의 설명 */
    private String targetDesc;

    /** 이 객체를 참조하는 부모 MapValidator 객체 */
    private MapValidator parentNode = null;

    /** null 검사 여부 */
    private boolean isNotNull = false;
    /** null 및 blank string 검사 여부 */
    private boolean isNotBlank = false;

    /** 문자열 최소길이 검사값 (-1이면 검사 안함) */
    private int minLength = -1;
    /** 문자열 최대길이 검사값 (-1이면 검사 안함) */
    private int maxLength = -1;

    /** 배열 최소길이 검사값 (-1이면 검사 안함) */
    private int arrayMin = -1;
    /** 배열 최대길이 검사값 (-1이면 검사 안함) */
    private int arrayMax = -1;

    /** 문자열 검사 정규식 */
    private String regex = null;
    /** 정규식 검사 오류시 출력할 메세지 */
    private String patternErrorMessage = null;


    /** 이전 날짜 검사 여부 */
    private boolean isPast = false;
    /** 이전 날짜 검사 기준일 */
    private LocalDateTime pastDateTime = null;
    /** 이전 날짜 or 현재 검사 여부 */
    private boolean isPastOrPresent = false;
    /** 이전 날짜 or 현재 검사 기준일 */
    private LocalDateTime pastOrPresentDateTime = null;
    /** 이후 날짜 검사 여부 */
    private boolean isFuture = false;
    /** 이후 날짜 검사 기준일 */
    private LocalDateTime futureDateTime = null;
    /** 이후 날짜 or 현재 검사 여부 */
    private boolean isFutureOrPresent = false;
    /** 이후 날짜 or 현재 검사 기준일 */
    private LocalDateTime futureOrPresentDateTime = null;
    /** 날짜 검사시 사용할 dateFormat 문자열 */
    private String dateFormat = "yyyyMMddHHmmss";

    /** 이메일 검사 여부 */
    private boolean isEmail = false;
    /** 휴대전화 검사 여부 */
    private boolean isCellPhone = false;

    /** 숫자 최소값 검사값 */
    private BigDecimal decimalMin = null;
    /** 숫자 최대값 검사값 */
    private BigDecimal decimalMax = null;

    /**
     * 생성자
     * @param targetName Map 객체에서 검사할 key
     */
    public MapValidateRule(String targetName) {
        this.targetName = targetName;
        this.targetDesc = targetName;
    }

    /**
     * 생성자
     * @param targetName Map 객체에서 검사할 key
     * @param targetDesc Map 객체에서 검사할 key의 설명
     */
    public MapValidateRule(String targetName, String targetDesc) {
        this.targetName = targetName;
        this.targetDesc = targetDesc;
    }

    /**
     * 생성자
     * @param targetName Map 객체에서 검사할 key
     * @param parentNode 이 객체를 참조하는 부모 MapValidator 객체
     */
    public MapValidateRule(String targetName, MapValidator parentNode) {
        this.targetName = targetName;
        this.targetDesc = targetName;
        this.parentNode = parentNode;
    }

    /**
     * 생성자
     * @param targetName Map 객체에서 검사할 key
     * @param targetDesc Map 객체에서 검사할 key의 설명
     * @param parentNode 이 객체를 참조하는 부모 MapValidator 객체
     */
    public MapValidateRule(String targetName, String targetDesc, MapValidator parentNode) {
        this.targetName = targetName;
        this.targetDesc = targetDesc;
        this.parentNode = parentNode;
    }

    /**
     * Map 객체에서 검사할 key 가져오기
     * @return
     */
    protected String getTargetName() {
        return this.targetName;
    }

    /**
     * Map 객체에서 검사할 key 넣기
     * @param targetName Map 객체에서 검사할 key
     */
    protected void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * Map 객체에서 검사할 key의 설명 가져오기
     * @return
     */
    protected String getTargetDesc() {
        return this.targetDesc;
    }

    /**
     * Map 객체에서 검사할 key의 설명 넣기
     * @param targetDesc Map 객체에서 검사할 key의 설명
     */
    protected void setTargetDesc(String targetDesc) {
        this.targetDesc = targetDesc;
    }

    /**
     * 이 객체를 참조하는 부모 MapValidator 객체 가져오기
     * @return
     */
    protected MapValidator getParentNode() {
        return this.parentNode;
    }

    /**
     * 이 객체를 참조하는 부모 MapValidator 객체 넣기
     * @param parentNode 이 객체를 참조하는 부모 MapValidator 객체
     */
    protected void setParentNode(MapValidator parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * null 검사하도록 설정
     * @return
     */
    public MapValidateRule notNull() {
        this.isNotNull = true;
        return this;
    }

    /**
     * null 검사하는지 여부
     * @return
     */
    protected boolean isNotNull() {
        return this.isNotNull;
    }

    /**
     * null 및 blank string 검사하도록 설정
     * @return
     */
    public MapValidateRule notBlank() {
        this.isNotBlank = true;
        return this;
    }

    /**
     * null 및 blank string 검사하는지 여부
     * @return
     */
    protected boolean isNotBlank() {
        return this.isNotBlank;
    }

    /**
     * 최소길이 검사하도록 설정
     * @param minLength 최소길이 검사값
     * @return
     */
    public MapValidateRule minLength(int minLength) {
        if(minLength >= 0) {
            this.minLength = minLength;
        }
        return this;
    }

    /**
     * 최소길이 검사값 가져오기
     * @return
     */
    protected int getMinLength() {
        return this.minLength;
    }

    /**
     * 최대길이 검사하도록 설정
     * @param maxLength 최대길이 검사값
     * @return
     */
    public MapValidateRule maxLength(int maxLength) {
        if(maxLength >= 0) {
            this.maxLength = maxLength;
        }
        return this;
    }

    /**
     * 최대길이 검사값 가져오기
     * @return
     */
    protected int getMaxLength() {
        return this.maxLength;
    }

    /**
     * 배열 최소길이 검사하도록 설정
     * @param arrayMin 배열 최소길이 검사값
     * @return
     */
    public MapValidateRule arrayMin(int arrayMin) {
        if(arrayMin >= 0) {
            this.arrayMin = arrayMin;
        }
        return this;
    }

    /**
     * 배열 최소길이 검사값 가져오기
     * @return
     */
    protected int getArrayMin() {
        return this.arrayMin;
    }

    /**
     * 배열 최대길이 검사하도록 설정
     * @param arrayMax 배열 최대길이 검사값
     * @return
     */
    public MapValidateRule arrayMax(int arrayMax) {
        if(arrayMax >= 0) {
            this.arrayMax = arrayMax;
        }
        return this;
    }

    /**
     * 배열 최대길이 검사값 가져오기
     * @return
     */
    protected int getArrayMax() {
        return this.arrayMax;
    }

    /**
     * 정규식 검사하도록 설정
     * @param regex 검사할 정규식
     * @return
     */
    public MapValidateRule pattern(String regex) {
        this.regex = regex;
        this.patternErrorMessage = null;
        return this;
    }

    /**
     * 정규식 검사하도록 설정
     * @param regex 검사할 정규식
     * @param errorMessage 정규식 검사 오류시 출력할 메세지
     * @return
     */
    public MapValidateRule pattern(String regex, String errorMessage) {
        this.regex = regex;
        this.patternErrorMessage = errorMessage;
        return this;
    }

    /**
     * 검사할 정규식 가져오기
     * @return
     */
    protected String getPattern() {
        return this.regex;
    }

    /**
     * 정규식 검사 오류시 출력할 메세지 가져오기
     * @return
     */
    protected String getPatternErrorMessage() {
        return this.patternErrorMessage;
    }

    /**
     * 오늘 날짜 기준으로 이전인지 검사하도록 설정
     * @return
     */
    public MapValidateRule past() {
        this.isPast = true;
        return this;
    }

    /**
     * 오늘 기준으로 이전인지 검사하도록 설정
     * @param dateFormat 날짜포맷
     * @return
     */
    public MapValidateRule past(String dateFormat) {
        setDateFormat(dateFormat);
        return past();
    }

    /**
     * 특정 날짜 기준으로 이전인지 검사하도록 설정
     * @return
     */
    public MapValidateRule past(LocalDateTime dateTime) {
        this.pastDateTime = dateTime;
        return past();
    }

    /**
     * 특정 날짜 기준으로 이전인지 검사하도록 설정
     * @return
     */
    public MapValidateRule past(LocalDateTime dateTime, String dateFormat) {
        setDateFormat(dateFormat);
        return past(dateTime);
    }

    /**
     * 오늘 or 특정날짜 기준으로 이전인지 검사 여부
     * @return
     */
    protected boolean isPast() {
        return this.isPast;
    }

    /**
     * 이전 검사 기준일 가져오기
     * @return
     */
    protected LocalDateTime getPastDateTime() {
        if(this.pastDateTime == null) {
            return LocalDateTime.now();
        }
        return this.pastDateTime;
    }

    /**
     * 오늘 기준으로 이전 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule pastOrPresent() {
        this.isPastOrPresent = true;
        return this;
    }

    /**
     * 오늘 기준으로 이전 혹은 현재인지 검사하도록 설정
     * @param dateFormat 날짜포맷
     * @return
     */
    public MapValidateRule pastOrPresent(String dateFormat) {
        setDateFormat(dateFormat);
        return pastOrPresent();
    }

    /**
     * 특정 날짜 기준으로 이전 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule pastOrPresent(LocalDateTime dateTime) {
        this.pastOrPresentDateTime = dateTime;
        return pastOrPresent();
    }

    /**
     * 특정 날짜 기준으로 이전 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule pastOrPresent(LocalDateTime dateTime, String dateFormat) {
        setDateFormat(dateFormat);
        return pastOrPresent(dateTime);
    }

    /**
     * 오늘 or 특정 날짜 기준으로 이전 혹은 현재인지 검사 여부
     * @return
     */
    protected boolean isPastOrPresent() {
        return this.isPastOrPresent;
    }

    /**
     * 이전 혹은 현재 검사 기준일 가져오기
     * @return
     */
    protected LocalDateTime getPastOrPresentDateTime() {
        if(this.pastOrPresentDateTime == null) {
            return LocalDateTime.now();
        }
        return this.pastOrPresentDateTime;
    }

    /**
     * 오늘 기준으로 이후인지 검사하도록 설정
     * @return
     */
    public MapValidateRule future() {
        this.isFuture = true;
        return this;
    }

    /**
     * 오늘 기준으로 이후인지 검사하도록 설정
     * @param dateFormat 날짜포맷
     * @return
     */
    public MapValidateRule future(String dateFormat) {
        setDateFormat(dateFormat);
        return future();
    }

    /**
     * 특정 날짜 기준으로 이후인지 검사하도록 설정
     * @return
     */
    public MapValidateRule future(LocalDateTime dateTime) {
        this.futureDateTime = dateTime;
        return future();
    }

    /**
     * 특정 날짜 기준으로 이후인지 검사하도록 설정
     * @return
     */
    public MapValidateRule future(LocalDateTime dateTime, String dateFormat) {
        setDateFormat(dateFormat);
        return future(dateTime);
    }

    /**
     * 오늘 or 특정 날짜기준으로 이후인지 검사 여부
     * @return
     */
    protected boolean isFuture() {
        return this.isFuture;
    }

    /**
     * 이후 검사 기준일 가져오기
     * @return
     */
    protected LocalDateTime getFutureDateTime() {
        if(this.futureDateTime == null) {
            return LocalDateTime.now();
        }
        return this.futureDateTime;
    }

    /**
     * 오늘 기준으로 이후 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule futureOrPresent() {
        this.isFutureOrPresent = true;
        return this;
    }

    /**
     * 오늘 기준으로 이후 혹은 현재인지 검사하도록 설정
     * @param dateFormat 날짜포맷
     * @return
     */
    public MapValidateRule futureOrPresent(String dateFormat) {
        setDateFormat(dateFormat);
        return futureOrPresent();
    }

    /**
     * 특정 날짜 기준으로 이후 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule futureOrPresent(LocalDateTime dateTime) {
        this.futureOrPresentDateTime = dateTime;
        return futureOrPresent();
    }

    /**
     * 특정 날짜 기준으로 이후 혹은 현재인지 검사하도록 설정
     * @return
     */
    public MapValidateRule futureOrPresent(LocalDateTime dateTime, String dateFormat) {
        setDateFormat(dateFormat);
        return futureOrPresent(dateTime);
    }

    /**
     * 오늘 or 특정 날짜 기준으로 이후 혹은 현재인지 검사 여부
     * @return
     */
    protected boolean isFutureOrPresent() {
        return this.isFutureOrPresent;
    }

    /**
     * 이후 혹은 현재 검사 기준일 가져오기
     * @return
     */
    protected LocalDateTime getFutureOrPresentDateTime() {
        if(this.futureOrPresentDateTime == null) {
            return LocalDateTime.now();
        }
        return this.futureOrPresentDateTime;
    }

    /**
     * 날짜 검사시 사용하는 포맷 넣기
     * @param dateFormat
     */
    protected void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 날짜 검사시 사용하는 포맷 가져오기
     * @return
     */
    protected String getDateFormat() {
        return this.dateFormat;
    }

    protected MapValidateRule getRule(String targetName) {
        if(parentNode != null) {
            return parentNode.getRule(targetName);
        }
        return null;
    }

    /**
     * 이메일 검사하도록 설정
     * @return
     */
    public MapValidateRule email() {
        this.isEmail = true;
        return this;
    }

    /**
     * 이메일 검사하는지 여부
     * @return
     */
    protected boolean isEmail() {
        return this.isEmail;
    }

    /**
     * 휴대전화번호 검사하도록 설정
     * @return
     */
    public MapValidateRule cellPhone() {
        this.isCellPhone = true;
        return this;
    }

    /**
     * 휴대전화번호 검사하는지 여부
     * @return
     */
    protected boolean isCellPhone() {
        return this.isCellPhone;
    }

    /**
     * 숫자 최소값 검사하도록 설정
     * @param min 최소값 기준
     * @return
     */
    public MapValidateRule decimalMin(int min) {
        return decimalMin(BigDecimal.valueOf(min));
    }

    /**
     * 숫자 최소값 검사하도록 설정
     * @param min 최소값 기준
     * @return
     */
    public MapValidateRule decimalMin(long min) {
        return decimalMin(BigDecimal.valueOf(min));
    }

    /**
     * 숫자 최소값 검사하도록 설정
     * @param min 최소값 기준
     * @return
     */
    public MapValidateRule decimalMin(double min) {
        return decimalMin(BigDecimal.valueOf(min));
    }

    /**
     * 숫자 최소값 검사하도록 설정
     * @param min 최소값 기준
     * @return
     */
    public MapValidateRule decimalMin(BigDecimal min) {
        this.decimalMin = min;
        return this;
    }

    /**
     * 숫자 최소값 검사 기준 가져오기
     * @return
     */
    protected BigDecimal getDecimalMin() {
        return this.decimalMin;
    }

    /**
     * 숫자 최대값 검사하도록 설정
     * @param max 최대값 기준
     * @return
     */
    public MapValidateRule decimalMax(int max) {
        return decimalMin(BigDecimal.valueOf(max));
    }

    /**
     * 숫자 최대값 검사하도록 설정
     * @param max 최대값 기준
     * @return
     */
    public MapValidateRule decimalMax(long max) {
        return decimalMax(BigDecimal.valueOf(max));
    }

    /**
     * 숫자 최대값 검사하도록 설정
     * @param max 최대값 기준
     * @return
     */
    public MapValidateRule decimalMax(double max) {
        return decimalMax(BigDecimal.valueOf(max));
    }

    /**
     * 숫자 최대값 검사하도록 설정
     * @param max 최대값 기준
     * @return
     */
    public MapValidateRule decimalMax(BigDecimal max) {
        this.decimalMax = max;
        return this;
    }

    /**
     * 숫자 최대값 검사 기준 가져오기
     * @return
     */
    protected BigDecimal getDecimalMax() {
        return this.decimalMax;
    }

    public MapValidateRule setRule(String targetName) {
        if(parentNode != null) {
            return parentNode.setRule(targetName);
        }
        return null;
    }

    public MapValidateRule setRule(String targetName, String targetDesc) {
        if(parentNode != null) {
            return parentNode.setRule(targetName, targetDesc);
        }
        return null;
    }

    public MapValidateRule setRule(MapValidateRule rule) {
        if(parentNode != null) {
            return parentNode.setRule(rule);
        }
        return null;
    }

    public MapValidator removeRule(String targetName) {
        if(parentNode != null) {
            return parentNode.removeRule(targetName);
        }
        return null;
    }

    public MapValidateResult validate() {
        if(parentNode != null) {
            return parentNode.validate();
        }
        return null;
    }

    @Override
    public MapValidateRule clone() {
        try {
            return (MapValidateRule) super.clone();
        } catch (Exception e) {
            return this;
        }
    }

}
