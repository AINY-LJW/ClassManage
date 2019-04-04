package com.lfh.classManage.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 *
 * @author lfh
 * @version 2019年3月14日
 */
@SuppressWarnings("deprecation")
@Configuration
public class LoginSecurityConfig implements WebMvcConfigurer {
	public final static String SESSION_KEY = "user";
	@Bean
	 public SecurityInterceptor getSecurityInterceptor() {
	   return new SecurityInterceptor();
	 }

	 public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
		 registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/**").excludePathPatterns("/login**", "/**/*.css", 
                 "/**/*.js", "/**/*.png", "/**/*.jpg", 
                 "/**/*.jpeg", "/**/*.gif", "/**/fonts/*");
//	   InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//
//	   // 排除配置
//	   addInterceptor.excludePathPatterns("/login", "/logout","classpath:/static");
//
//	   // 拦截配置
//	   addInterceptor.addPathPatterns("/**");
	 }

	 private class SecurityInterceptor extends HandlerInterceptorAdapter {

	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	     throws Exception {
	   HttpSession session = request.getSession();

	   if (session.getAttribute(SESSION_KEY) != null) {
	     return true;
	   }
	   response.sendRedirect("/login");
	  
	   return false;
	  }
	 }
}
