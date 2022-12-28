package com.tjetc.config;

import com.tjetc.domain.ExceptionResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.sql.SQLException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @ExceptionHandler
    @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="哈哈")
    public String handle(ValidationException exception) {
        System.out.println("bad request, " + exception.getMessage());
        return "bad request, " + exception.getMessage();
    }

}
