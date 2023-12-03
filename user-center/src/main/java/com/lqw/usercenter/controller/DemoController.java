package com.lqw.usercenter.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * @Description TODO
 * @Author lqw
 * @Date 2023/11/29 22:03
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping("/test")
    public Object test(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        log.info("headers: {}", headers);
        Enumeration<String> values = request.getHeaders("Host");
        System.out.println(JSON.toJSONString(values));
        return "test";
    }
    @RequestMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) {
        log.info("auth");
    }

    @RequestMapping("/download/**")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getServletPath();
        fileName = fileName.substring("/demo/download".length());
        log.info("fileName: {}", fileName);
        response.setHeader("X-Accel-Redirect", fileName);
    }
}
