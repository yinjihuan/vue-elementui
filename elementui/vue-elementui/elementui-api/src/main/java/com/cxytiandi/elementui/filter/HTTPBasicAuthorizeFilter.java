package com.cxytiandi.elementui.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cxytiandi.elementui.base.ResponseCode;
import com.cxytiandi.elementui.base.ResponseData;
import com.cxytiandi.elementui.config.ApiWhiteData;
import com.cxytiandi.elementui.utils.JWTUtils;
import com.cxytiandi.elementui.utils.JsonUtils;

/**
 * API 调用权限控制
 * @author yinjihuan
 *
 */
public class HTTPBasicAuthorizeFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();  
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8"); 
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        httpResponse.setHeader("Access-Control-Expose-Headers", "*");
        String auth = httpRequest.getHeader("Authorization");
        //白名单，放过
        if (ApiWhiteData.getWhiteApis().contains(httpRequest.getRequestURI())) {
        	chain.doFilter(httpRequest, response);
        	return;  
		}
        System.err.println(auth);
        //验证TOKEN
        if (!StringUtils.hasText(auth)) {
			PrintWriter print = httpResponse.getWriter();
        	print.write(JsonUtils.toJson(ResponseData.fail("非法请求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode())));  
            return;  
		}
        JWTUtils.JWTResult jwt = JWTUtils.checkToken(auth);
		if (!jwt.isStatus()) {
			PrintWriter print = httpResponse.getWriter();
			print.write(JsonUtils.toJson(ResponseData.fail(jwt.getMsg(), jwt.getCode())));  
			return;
		}
		
		chain.doFilter(httpRequest, response);
	}
	
	@Override
	public void destroy() {
		
	}

}
