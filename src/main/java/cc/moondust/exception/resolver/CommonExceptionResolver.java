package cc.moondust.exception.resolver;

import cc.moondust.exception.BaseException;
import cc.moondust.exception.BusinessException;
import cc.moondust.exception.ParamsException;
import cc.moondust.exception.UnKnowException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * Created by tc949 on 2017/3/21.
 */


@ControllerAdvice(annotations = {Controller.class, RestController.class}, basePackages = {"cc.moondust.controller"})
public class CommonExceptionResolver {

    @ResponseBody
    @ExceptionHandler(ParamsException.class)
    public Object doForParamException(ParamsException e, HttpServletResponse response) {
        return getErr(e, response);
    }



    @ResponseBody
    @ExceptionHandler(UnKnowException.class)
    public Object doForUnKnowException(UnKnowException e, HttpServletResponse response) {
        return getErr(e, response);
    }


    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Object doForBusinessException(BusinessException e, HttpServletResponse response) {
        return getErr(e, response);
    }


    private Object getErr(BaseException e, HttpServletResponse response) {
        response.setStatus(e.getCode());
        ResponseEntity entity = new ResponseEntity(e.getCode(), e.getMessage(), e.getStackTrace().toString());
        return entity;
    }

}
