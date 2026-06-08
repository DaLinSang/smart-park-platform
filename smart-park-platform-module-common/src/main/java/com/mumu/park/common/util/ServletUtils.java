package com.mumu.park.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URLEncoder;

public class ServletUtils {
    //客户端工具类
    //用来获取当前请求、响应、客户端IP、文件下载等

    private ServletUtils(){}

    public static void writeAttachment(HttpServletResponse response,String filename,byte[] content) throws IOException {
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        IoUtil.write(response.getOutputStream(),false,content);
    }

    public static String getClientIP(){
        HttpServletRequest request = getRequest();
        if (request == null){
            return null;
        }
        return JakartaServletUtil.getClientIP(request);
    }

    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) return null;
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

}
