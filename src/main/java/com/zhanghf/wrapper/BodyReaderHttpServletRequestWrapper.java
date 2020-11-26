package com.zhanghf.wrapper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:01 2020/11/26
 */
@Slf4j
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            body = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isReady() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                // TODO Auto-generated method stub
            }
        };
    }

}
