package com.zhanghf.filter;


import com.zhanghf.wrapper.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:37 2020/11/26
 */

@Slf4j
public class HttpServletRequestReplacedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("HttpServletRequestReplacedFilter.init");
        //Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("HttpServletRequestReplacedFilter.doFilter");
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
        log.info("HttpServletRequestReplacedFilter.destroy");
        //Do nothing
    }

}
