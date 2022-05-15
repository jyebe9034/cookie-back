package com.example.cookie.util.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Map Validator
 */
public class MapValidator {

    /** 검사할 대상 Map 객체 */
    private Map<String, Object> target;

    /** 검사 규칙 목록 */
    private Set<com.example.cookie.util.validator.MapValidateRule> rules;

    /** 결과 메세지 언어 [Locale.KOREAN|Locale.ENGLISH] */
    private Locale locale;

    /**
     * 생성자
     * @param target 검사할 대상 Map 객체
     */
    public MapValidator(Map<String, Object> target) {
        this.target = target;
        this.rules = new LinkedHashSet<>();
        setLocaleKorean();
    }

    /**
     * 생성자
     * @param target 검사할 대상 Map 객체
     * @param rules 검사 규칙 목록
     */
    public MapValidator(Map<String, Object> target, Set<com.example.cookie.util.validator.MapValidateRule> rules) {
        this.target = target;
        this.rules = rules;
        setLocaleKorean();
    }

    /**
     * 생성자
     * @param target 검사할 대상 Map 객체
     * @param locale 결과 메세지 언어
     */
    public MapValidator(Map<String, Object> target, Locale locale) {
        this.target = target;
        this.rules = new LinkedHashSet<>();
        setLocale(locale);
    }

    /**
     * 생성자
     * @param target 검사할 대상 Map 객체
     * @param rules 검사 규칙 목록
     * @param locale 결과 메세지 언어
     */
    public MapValidator(Map<String, Object> target, Set<com.example.cookie.util.validator.MapValidateRule> rules, Locale locale) {
        this.target = target;
        this.rules = rules;
        setLocale(locale);
    }

    /**
     * 검사할 대상 Map 객체 가져오기
     * @return
     */
    protected Map<String, Object> getTarget() {
        return this.target;
    }

    /**
     * 검사할 대상 Map 객체 넣기
     * @param target 검사할 대상 Map 객체
     * @return
     */
    protected MapValidator setTarget(Map<String, Object> target) {
        this.target = target;
        return this;
    }

    /**
     * 검사 규칙 목록 가져오기
     * @return
     */
    protected Set<com.example.cookie.util.validator.MapValidateRule> getRules() {
        return this.rules;
    }

    /**
     * 검사 규칙 가져오기
     * @param targetName 가져올 검사 규칙의 대상 이름
     * @return
     */
    public com.example.cookie.util.validator.MapValidateRule getRule(String targetName) {
        com.example.cookie.util.validator.MapValidateRule targetRule = null;
        if(targetName != null) {
            for (com.example.cookie.util.validator.MapValidateRule rule : this.rules) {
                if(targetName.equals(rule.getTargetName())) {
                    targetRule = rule;
                    break;
                }
            }
        }
        return targetRule;
    }

    /**
     * 검사 규칙 넣기
     * @param targetName Map 객체에서 검사할 key
     * @return
     */
    public com.example.cookie.util.validator.MapValidateRule setRule(String targetName) {
        return setRule(targetName, targetName);
    }

    /**
     * 검사 규칙 넣기
     * @param targetName Map 객체에서 검사할 key
     * @param targetDesc Map 객체에서 검사할 key의 설명
     * @return
     */
    public com.example.cookie.util.validator.MapValidateRule setRule(String targetName, String targetDesc) {
        com.example.cookie.util.validator.MapValidateRule rule = new com.example.cookie.util.validator.MapValidateRule(targetName, targetDesc, this);
        this.rules.add(rule);
        return rule;
    }

    /**
     * 검사 규칙 넣기
     * @param rule 검사 규칙 객체
     * @return
     */
    public com.example.cookie.util.validator.MapValidateRule setRule(com.example.cookie.util.validator.MapValidateRule rule) {
        rule = rule.clone();
        rule.setParentNode(this);
        this.rules.add(rule);
        return rule;
    }

    /**
     * 검사 규칙 제거
     * @param targetName 제거할 검사 규칙의 대상 이름
     * @return
     */
    public MapValidator removeRule(String targetName) {
        if(targetName != null) {
            this.rules.remove(getRule(targetName));
        }
        return this;
    }

    /**
     * 결과 메세지 언어 넣기
     * @param locale 결과 메세지 언어
     * @return
     */
    public MapValidator setLocale(Locale locale) {
        if(locale == null || (!locale.equals(Locale.KOREAN) && !locale.equals(Locale.ENGLISH))) {
            // null 이거나 한국어, 영어가 아닌 경우 한국어로 설정
            setLocaleKorean();
        } else {
            this.locale = locale;
        }
        return this;
    }

    /**
     * 결과 메세지 언어 한국어로 설정하기
     * @return
     */
    public MapValidator setLocaleKorean() {
        setLocale(Locale.KOREAN);
        return this;
    }

    /**
     * 결과 메세지 언어가 한국어인지 여부
     * @return
     */
    public boolean isLocaleKorean() {
        return this.locale.equals(Locale.KOREAN);
    }

    /**
     * 결과 메세지 언어 영어로 설정하기
     * @return
     */
    public MapValidator setLocaleEnglish() {
        setLocale(Locale.ENGLISH);
        return this;
    }

    /**
     * 결과 메세지 언어가 영어인지 여부
     * @return
     */
    public boolean isLocaleEnglish() {
        return this.locale.equals(Locale.ENGLISH);
    }

    /**
     * 유효성 검사 실행
     * @return
     */
    public MapValidateResult validate() {
        MapValidateResult result = new MapValidateResult();
        try {
            for (com.example.cookie.util.validator.MapValidateRule rule : this.rules) {
                String targetName = rule.getTargetName();
                String targetDesc = rule.getTargetDesc();
                Object targetObject = getTargetObject(this.target, targetName);
                List<Object> targetValues;
                if(targetObject instanceof List) {
                    // 배열인 경우
                    targetValues = (List<Object>) targetObject;
                } else {
                    // 배열이 아닌경우
                    targetValues = new ArrayList<>();
                    targetValues.add(targetObject);
                }
                // arrayMin
                if(rule.getArrayMin() >= 0 && targetValues.size() < rule.getArrayMin()) {
                    String errorMessage = this.isLocaleKorean()
                            ? targetDesc + " 배열은 길이가 " + rule.getArrayMin() + "보다 크거나 같아야 합니다."
                            : targetName + " array length must be greater than or equal to " + rule.getArrayMin();
                    result.addErrorMessage(targetName,errorMessage);
                }
                // arrayMax
                if(rule.getArrayMax() >= 0 && targetValues.size() > rule.getArrayMax()) {
                    String errorMessage = this.isLocaleKorean()
                            ? targetDesc + " 배열은 길이가 " + rule.getArrayMax() + "보다 작거나 같아야 합니다."
                            : targetName + " array length must be less than or equal to " + rule.getArrayMin();
                    result.addErrorMessage(targetName, errorMessage);
                }
                int targetIndex = 0;
                for (Object targetValue : targetValues) {
                    if(targetValues.size() > 1) {
                        targetDesc += "[" + targetIndex +"]";
                    }
                    // notNull
                    if(rule.isNotNull() && targetValue == null) {
                        String errorMessage = this.isLocaleKorean()
                                ? targetDesc + "(은)는 null이 아니어야 합니다."
                                : targetName + " must be not null";
                        result.addErrorMessage(targetName, errorMessage);
                    }
                    // notBlank
                    if(rule.isNotBlank() && (targetValue == null || "".equals(targetValue))) {
                        String errorMessage = this.isLocaleKorean()
                                ? targetDesc + "(은)는 필수입력 입니다."
                                : targetName + " must be not blank";
                        result.addErrorMessage(targetName, errorMessage);
                    }
                    if(targetValue != null) {
                        // minLength
                        if(rule.getMinLength() >= 0 && String.valueOf(targetValue).length() < rule.getMinLength()) {
                            String errorMessage = this.isLocaleKorean()
                                    ? targetDesc + "(은)는 길이가 " + rule.getMinLength() + "보다 크거나 같아야합니다."
                                    : targetName + " length must be greater than or equal to " + rule.getMinLength();
                            result.addErrorMessage(targetName, errorMessage);
                        }
                        // maxLength
                        if(rule.getMaxLength() >= 0 && String.valueOf(targetValue).length() > rule.getMaxLength()) {
                            String errorMessage = this.isLocaleKorean()
                                    ? targetDesc + "(은)는 길이가 " + rule.getMaxLength() + "보다 작거나 같아야합니다."
                                    : targetName + " length must be less than or equal to " + rule.getMinLength();
                            result.addErrorMessage(targetName, targetDesc);
                        }
                        // pattern
                        if(rule.getPattern() != null && !String.valueOf(targetValue).matches(rule.getPattern())) {
                            String errorMessage = this.isLocaleKorean()
                                    ? targetDesc + "(은)는 " + rule.getPattern() + " 조건을 만족해야합니다."
                                    : targetName + " must match " + rule.getPattern();
                            if(rule.getPatternErrorMessage() != null) {
                                errorMessage = rule.getPatternErrorMessage();
                            }
                            result.addErrorMessage(targetName, errorMessage);
                        }
                        // past, pastOrPresent, future, futureOrPresent
                        if(rule.isPast() || rule.isPastOrPresent() || rule.isFuture() || rule.isFutureOrPresent()) {
                            try {
                                DateTimeFormatter format = DateTimeFormatter.ofPattern(rule.getDateFormat());
                                // past
                                if (rule.isPast()) {
                                    LocalDateTime standardDateTime = rule.getPastDateTime();
                                    LocalDateTime targetDateTime = getTargetDateTime(String.valueOf(targetValue), rule.getDateFormat(), standardDateTime);
                                    if (!targetDateTime.isBefore(standardDateTime) || targetDateTime.isEqual(standardDateTime)) {
                                        String errorMessage = this.isLocaleKorean()
                                                ? targetDesc + "(은)는 " + standardDateTime.format(format) + "보다 이전이어야 합니다."
                                                : targetName + " must be before " + standardDateTime.format(format);
                                        result.addErrorMessage(targetName, errorMessage);
                                    }
                                }
                                // pastOrPresent
                                if (rule.isPastOrPresent()) {
                                    LocalDateTime standardDateTime = rule.getPastOrPresentDateTime();
                                    LocalDateTime targetDateTime = getTargetDateTime(String.valueOf(targetValue), rule.getDateFormat(), standardDateTime);
                                    if (!targetDateTime.isBefore(standardDateTime) && !targetDateTime.isEqual(standardDateTime)) {
                                        String errorMessage = this.isLocaleKorean()
                                                ? targetDesc + "(은)는 " + standardDateTime.format(format) + "보다 이전이거나 같아야 합니다."
                                                : targetName + " must be before or equal to " + standardDateTime.format(format);
                                        result.addErrorMessage(targetName, errorMessage);
                                    }
                                }
                                // future
                                if (rule.isFuture()) {
                                    LocalDateTime standardDateTime = rule.getPastOrPresentDateTime();
                                    LocalDateTime targetDateTime = getTargetDateTime(String.valueOf(targetValue), rule.getDateFormat(), standardDateTime);
                                    if (!targetDateTime.isAfter(standardDateTime) || targetDateTime.isEqual(standardDateTime)) {
                                        String errorMessage = this.isLocaleKorean()
                                                ? targetDesc + "(은)는 " + standardDateTime.format(format) + "보다 이후어야 합니다."
                                                : targetName + " must be after " + standardDateTime.format(format);
                                        result.addErrorMessage(targetName, errorMessage);
                                    }
                                }
                                // futureOrPresent
                                if (rule.isFutureOrPresent()) {
                                    LocalDateTime standardDateTime = rule.getFutureOrPresentDateTime();
                                    LocalDateTime targetDateTime = getTargetDateTime(String.valueOf(targetValue), rule.getDateFormat(), standardDateTime);
                                    if (!targetDateTime.isAfter(standardDateTime) && !targetDateTime.isEqual(standardDateTime)) {
                                        String errorMessage = this.isLocaleKorean()
                                                ? targetDesc + "(은)는 " + standardDateTime.format(format) + "보다 이후거나 같아야 합니다."
                                                : targetName + " must be after or equal to " + standardDateTime.format(format);
                                        result.addErrorMessage(targetName, errorMessage);
                                    }
                                }
                            } catch (DateTimeParseException dtpe) {
                                String errorMessage = this.isLocaleKorean()
                                        ? targetValue + "(은)는 날짜 형식의 문자열이어야 합니다."
                                        : targetValue + " must be date format string.";
                                result.addErrorMessage(targetName, errorMessage);
                            } catch (Exception e) {
                                String errorMessage = this.isLocaleKorean()
                                        ? "날짜 유효성 검사 중 오류가 발생하였습니다."
                                        : "An error occurred during date validation.";
                                result.addErrorMessage(targetName, errorMessage);
                            }
                        }
                        // email
                        if(rule.isEmail() && !String.valueOf(targetValue).matches("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")) {
                            String errorMessage = this.isLocaleKorean()
                                    ? targetDesc + "(은)는 이메일 형식이어야 합니다."
                                    : targetName + " not a well-formed email address";
                            result.addErrorMessage(targetName, errorMessage);
                        }
                        // cellPhone
                        if(rule.isCellPhone()) {
                            String targetPhoneNumber = String.valueOf(targetValue).replaceAll("-", "");
                            if(!targetPhoneNumber.matches("^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$")) {
                                String errorMessage = this.isLocaleKorean()
                                        ? targetDesc + "(은)는 휴대전화번호 형식이어야 합니다."
                                        : targetName + " not a well-formed cell phone";
                                result.addErrorMessage(targetName, errorMessage);
                            }
                        }
                        // decimalMin
                        if(rule.getDecimalMin() != null || rule.getDecimalMax() != null) {
                            boolean isValid;
                            try {
                                BigDecimal targetDecimal = new BigDecimal(String.valueOf(targetValue));
                                isValid = targetDecimal.compareTo(rule.getDecimalMin()) >= 0;
                            } catch (Exception e) {
                                isValid = false;
                            }
                            if(!isValid) {
                                String errorMessage = this.isLocaleKorean()
                                        ? targetDesc + "(은)는 " + rule.getDecimalMin() + "보다 크거나 같아야 합니다."
                                        : targetName + " must be greater than or equal to  " + rule.getDecimalMin();
                                result.addErrorMessage(targetName, errorMessage);
                            }
                        }
                        // decimalMax
                        if(rule.getDecimalMax() != null) {
                            boolean isValid;
                            try {
                                BigDecimal targetDecimal = new BigDecimal(String.valueOf(targetValue));
                                isValid = targetDecimal.compareTo(rule.getDecimalMax()) <= 0;
                            } catch (Exception e) {
                                isValid = false;
                            }
                            if(!isValid) {
                                String errorMessage = this.isLocaleKorean()
                                        ? targetDesc + "(은)는 " + rule.getDecimalMax() + "보다 작거나 같아야 합니다."
                                        : targetName + " must be less than or equal to  " + rule.getDecimalMax();
                                result.addErrorMessage(targetName, errorMessage);
                            }
                        }
                    }
                    targetIndex++;
                }
            }
        } catch (Exception e) {
            result.addErrorMessage(null, "유효성 검사 중 오류가 발생하였습니다.");
        }
        return result;
    }

    private LocalDateTime getTargetDateTime(String targetValue, String formatString, LocalDateTime standardDateTime) {
        if(standardDateTime == null) {
            standardDateTime = LocalDateTime.now();
        }
        LocalDateTime targetDateTime;
        if("yyyyMM".equals(formatString)) {
            // 년월의 경우 format을 년월일로 바꾸고, 해당 월의 마지막날을 계산하여 넣는다.
            formatString = "yyyyMMdd";
            DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
            LocalDate targetDate = YearMonth.from(LocalDate.parse(targetValue + "01", format)).atEndOfMonth();
            targetValue = targetDate.format(format);
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        if(formatString.contains("HH")) {
            targetDateTime = LocalDateTime.parse(String.valueOf(targetValue), format);
        } else {
            targetDateTime = LocalDateTime.of(LocalDate.parse(String.valueOf(targetValue), format), standardDateTime.toLocalTime());
        }
        return targetDateTime;
    }

    /**
     * 검사 규칙에서 대상 객체 추출
     * @param target
     * @param targetName
     * @return
     */
    private Object getTargetObject(Map<String, Object> target, String targetName) {
        Object targetObject;
        if(targetName.indexOf(".") > 0) {
            String nowTargetName = targetName.substring(0, targetName.indexOf("."));
            targetObject = getTargetObject((Map<String, Object>) target.get(nowTargetName), targetName.substring(targetName.indexOf(".") + 1));
        } else {
            targetObject = target.get(targetName);
        }
        return targetObject;
    }

}
