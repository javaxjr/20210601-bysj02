package com.tjetc.error;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * 捕获springmvc请求异常信息---跳转至指定错误页面
 */
public class ErrorController {

    public class ControllerExceptionAdvice implements HandlerExceptionResolver {

        @Override
        public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            ModelAndView modelAndView = new ModelAndView("error/500");

            if (ex instanceof AccessDeniedException) {
                modelAndView.setViewName("type/add");
            } else {
                modelAndView.setViewName("type/add");
            }
            return modelAndView;
        }
    }
}
