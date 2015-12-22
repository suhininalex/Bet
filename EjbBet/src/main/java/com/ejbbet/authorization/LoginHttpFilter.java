package com.ejbbet.authorization;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHttpFilter implements Filter{
    
    String loginBeanName;
    String redirectAdress;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginBeanName = filterConfig.getInitParameter("loginBeanName");
        redirectAdress = filterConfig.getInitParameter("redirectAdress");
        if ((loginBeanName==null) || (redirectAdress==null)) 
            throw new ServletException("LoginHttpFilter: loginBeanName or redirectAdress initParam is not specified.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String url = httpRequest.getRequestURI();
        if (!url.contains(redirectAdress)) checkAndRedirect(httpRequest, httpResponse);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
    
    private void checkAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
        AuthorizationBean authorization = (AuthorizationBean) request.getSession().getAttribute(loginBeanName);
        if (authorization==null || !authorization.isIsSignIn()) response.sendRedirect(redirectAdress);
    }
}
