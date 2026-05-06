package com.gguek.app.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.review.dto.ReviewDTO;
import com.gguek.app.review.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review/*")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("list")
	public void list(ReviewDTO reviewDTO, Model model) throws Exception{
		// 상품 번호가 제대로 넘어왔는지 확인!
	    System.out.println("넘어온 상품 번호: " + reviewDTO.getProductNum());
	    
	    List<ReviewDTO> ar = reviewService.list(reviewDTO);
	    System.out.println("가져온 리뷰 개수: " + ar.size());
	    
	    model.addAttribute("list", ar);
	    
	    
	}
	
	@PostMapping("create")
	@ResponseBody
	public int create(HttpSession session, ReviewDTO reviewDTO) {
	    // 1. 로그인 체크
	    MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
	    if (memberDTO == null) {
	        System.out.println("리뷰 등록 실패: 세션 만료 또는 미로그인");
	        return -1;
	    }

	    try {
	        // 2. 데이터 세팅 확인 로그
	        reviewDTO.setUsername(memberDTO.getUsername());
	        System.out.println("리뷰 등록 시도: " + reviewDTO.toString());

	        int result = reviewService.create(reviewDTO);
	        
	        if(result > 0) {
	            System.out.println("리뷰 등록 성공!");
	        } else {
	            System.out.println("리뷰 등록 실패: DB 반영 결과가 0임");
	        }
	        
	        return result;

	    } catch (Exception e) {
	        // 3. 에러 상세 로그 (이 부분이 가장 중요합니다)
	        System.err.println("=== 리뷰 등록 중 예외 발생 ===");
	        System.err.println("에러 메시지: " + e.getMessage()); 
	        e.printStackTrace(); // 전체 스택트레이스 출력
	        System.err.println("============================");
	        
	        return 0; // JS에게 0을 보내 "알 수 없는 오류"임을 알림
	    }
	}
	
	
	// 리뷰 수정 처리
	@PostMapping("update")
	@ResponseBody
	public String update(ReviewDTO reviewDTO, HttpSession session) throws Exception{
	    // 1. 세션에서 로그인한 유저 정보 가져오기
	    MemberDTO member = (MemberDTO) session.getAttribute("member");
	    
	    // 2. 본인 확인을 위해 DTO에 username 세팅
	    if (member != null) {
	        reviewDTO.setUsername(member.getUsername());
	        
	        // 3. 서비스 호출 (성공 시 1, 실패 시 0 반환 예상)
	        int result = reviewService.update(reviewDTO);
	        return String.valueOf(result);
	    }
	    
	    return "0"; // 로그인 안 되어 있으면 실패
	}
	
	
	
	
	
	
	
	
	
	
	

	// 리뷰 삭제 처리
	@PostMapping("delete")
	@ResponseBody
	public String delete(@RequestParam("reviewNum") Long reviewNum, HttpSession session) throws Exception{
	    MemberDTO member = (MemberDTO) session.getAttribute("member");
	    
	    if (member != null) {
	        ReviewDTO reviewDTO = new ReviewDTO();
	        reviewDTO.setReviewNum(reviewNum);
	        reviewDTO.setUsername(member.getUsername());
	        
	        int result = reviewService.delete(reviewDTO);
	        return String.valueOf(result);
	    }
	    
	    return "0";
	}
}
