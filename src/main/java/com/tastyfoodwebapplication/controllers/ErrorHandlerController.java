//package com.tastyfoodwebapplication.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//@Controller
//public class ErrorHandlerController {
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.FORBIDDEN.value()) {
//                return "/access-denied";
//            }
//        }
//        return "/error";
//    }
//}
