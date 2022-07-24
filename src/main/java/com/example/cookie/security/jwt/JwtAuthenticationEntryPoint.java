package com.example.cookie.security.jwt;

import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String message = (String) request.getAttribute("message");
        Map<String, Object> resultMsg = MessageUtil.setResultMsg(Message.시스템오류, message);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(new Gson().toJson(resultMsg));
    }
}
