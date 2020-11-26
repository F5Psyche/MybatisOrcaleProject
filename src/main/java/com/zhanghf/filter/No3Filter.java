package com.zhanghf.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:11 2020/11/26
 */
@Slf4j
public class No3Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("No3Filter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("No3Filter.doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("No3Filter.destroy");
    }


}
