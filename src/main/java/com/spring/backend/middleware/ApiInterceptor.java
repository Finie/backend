package com.spring.backend.middleware;

import com.spring.backend.exceptions.UnAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ApiInterceptor implements HandlerInterceptor {

    /**
     * This middleware interceptor
     * validates that the request
     * contains an apiKey
     * */

    @Value("${api.key}")
    private String valid_key;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String apiKey = request.getHeader("api_key");

        System.out.println("Incoming key: "+apiKey);
        System.out.println("Current key: "+valid_key);

        if (apiKey == null || !apiKey.equals(valid_key)) {
            throw new UnAuthorizedException("Invalid api-key: "+ apiKey);
        }

         return true;
    }

}
