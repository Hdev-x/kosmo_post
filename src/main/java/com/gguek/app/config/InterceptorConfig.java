package com.gguek.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gguek.app.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private TestInterceptor testInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		//적용할 Interceptor 등록
		registry.addInterceptor(testInterceptor)
				//Interceptor를 사용할 URL 패턴 작성, addPathPatterns 여러번 호출 가능
				.addPathPatterns("/notice/*", "/qna/*")
				//Interceptor를 제외할 URL 패턴 작성, excludePathPaterns 여러번 호출 가능
				.excludePathPatterns("/notice/detail")
				;
		
		
	}
	
	
	
}
