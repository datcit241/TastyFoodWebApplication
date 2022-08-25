package com.tastyfoodwebapplication.config;

import org.springframework.http.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.toString() : "Unknown error");

        model.addAttribute("errorMessage", errorMessage);
        return "/error";
    }

}
