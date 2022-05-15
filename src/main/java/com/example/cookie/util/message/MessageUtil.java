package com.example.cookie.util.message;

import com.example.cookie.util.validator.MapValidateResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageUtil {
    public static Map<String, Object> checkMapValidate(MapValidateResult validateResult) {
        Map<String, Object> result = new LinkedHashMap<>();

        if(!validateResult.isValid()) {
            String resultCode = Message.시스템오류.getCode();
            for(Message obj : Message.values()) {
                if(obj.getTarget().equals(validateResult.getErrorTargetName())) {
                    resultCode = obj.getCode();
                    break;
                }
            }

            result.put("resultCode", resultCode);
            result.put("resultMsg", validateResult.getErrorMessage());
        } else {
            result.put("resultCode", Message.성공.getCode());
        }

        return result;
    }

    public static Map<String, Object> setResultMsg(Message message) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("resultCode", message.getCode());
        resultMap.put("resultMsg", message.getMessage());
        return resultMap;
    }

    public static Map<String, Object> setResultMsg(Message message, String customMsg) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("resultCode", message.getCode());
        resultMap.put("resultMsg", customMsg);
        return resultMap;
    }
}
