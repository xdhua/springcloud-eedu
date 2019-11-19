package com.hxd.security.core.utils;

import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;


public class CaptchaUtil {


    // gif 类型验证码
    private static final int GIF_TYPE = 1;
    // png 类型验证码
    private static final int PNG_TYPE = 0;

    // 验证码图片默认高度
    private static final int DEFAULT_HEIGHT = 48;
    // 验证码图片默认宽度
    private static final int DEFAULT_WIDTH = 130;
    // 验证码默认位数
    private static final int DEFAULT_LEN = 5;
    
    
    public static void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_LEN, request, response);
    }

    public static void out(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static void out(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    public static void out(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(width, height, len, vType, null, request, response);
    }

    public static Captcha out(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outCaptcha(width, height, len, font, GIF_TYPE, vType, response);
    }

    public static void outPng(HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_LEN, request, response);
    }

    public static void outPng(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static void outPng(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    /**
     *  获取验证码
     * @param width 宽度
     * @param height 高度
     * @param len 多少位
     * @param vType 什么类型
     * @param request request
     * @param response response
     * @throws IOException  异常信息
     */
    public static Captcha outPng(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outPng(width, height, len, vType, null, request, response);
    }

    public static Captcha outPng(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
       return outCaptcha(width, height, len, font, PNG_TYPE, vType, response);
    }


    private static Captcha outCaptcha(int width, int height, int len, Font font, int cType, Integer vType,  HttpServletResponse response) throws IOException {
        setHeader(response, cType);
        Captcha captcha = null;
//        是否设置为动态验证码
        if (cType == GIF_TYPE) {
            captcha = new GifCaptcha(width, height, len);
        } else {
            captcha = new SpecCaptcha(width, height, len);
        }
        if (font != null) {
            captcha.setFont(font);
        }
        if (vType != null) {
            captcha.setCharType(vType);
        }
//        	TODO;  将生成的 验证码存入内存
//        captcha.text(); 验证码内容
        return captcha;
    }

    /**
     *  设置 response header
     * @param response
     * @param cType
     */
    public static void setHeader(HttpServletResponse response, int cType) {
        if (cType == GIF_TYPE) {
            response.setContentType("image/gif");
        } else {
            response.setContentType("image/png");
        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }
}
