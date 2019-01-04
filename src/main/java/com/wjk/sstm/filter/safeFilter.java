package com.wjk.sstm.filter;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

//以后配置
//过滤器配置

public class safeFilter implements Filter {

//    实现filterchain的dofilter方法里面存在这种机制，把自身接收到的请求request对象和response对象和自身对象即filterchain
//
//    作为下一个过滤器的dofilter的三个形参传递过去，这样才能使得过滤器传递下去，当然这个方法中还存在一些判断if等机制
//
//    用来判断现在的这个过滤器是不是最后一个，是的话就可以把请求和响应对象传递给浏览器请求的页面
//    执行顺序：过滤前 – 拦截前 – Action处理 – 拦截后 – 过滤后。个人认为过滤是一个横向的过程
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain Chain) throws IOException, ServletException{
//        过滤前

        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) res);
        HttpServletRequest request = (HttpServletRequest) req;
        if(request.getRequestURI().indexOf("/music") != -1 || request.getRequestURI().indexOf("/api") != -1) {
            System.out.println("-----------------doFilter--------------");
            Chain.doFilter(req, res);
        }else {
            wrapper.sendRedirect("/login");
        }
//        跳转下个拦截器 如果下个拦截机为空则返回
//        Chain.doFilter(req,res);
//        函数回调
//        过滤后
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
