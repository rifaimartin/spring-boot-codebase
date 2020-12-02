package com.cashlez.demo.controller;
import com.cashlez.demo.dto.general.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    @ResponseBody
    public GeneralResponse exception(Exception e) {
        log.error("[ExceptionHandlerController] ERROR WITH MESSAGE: " + e.getMessage());
        e.printStackTrace();
        return new GeneralResponse().fail(e);
    }
}
