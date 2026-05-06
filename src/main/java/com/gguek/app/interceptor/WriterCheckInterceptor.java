package com.gguek.app.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WriterCheckInterceptor implements HandlerInterceptor{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Map<String, Object> model = modelAndView.getModel();
		BoardDTO boardDTO = (BoardDTO)model.get("dto");
		
		boolean flag = memberDTO.getUsername().equals(boardDTO.getBoardWriter());
		if (!flag) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("result", "작성자만 접근이 가능합니다.");
			modelAndView.addObject("url", "./list");
		}
	}
}
