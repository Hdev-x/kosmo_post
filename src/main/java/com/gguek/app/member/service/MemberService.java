package com.gguek.app.member.service;

import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.member.dto.ProfileDTO;

public interface MemberService {
	
	// 회원 가입
	int join(MemberDTO member, MultipartFile file) throws Exception;
	
	// 프로필 추가
	void addProfile(ProfileDTO profile) throws Exception;
	
	// 로그인
	MemberDTO detail(MemberDTO memberDTO) throws Exception;
	
	// 아이디 중복 확인
	boolean isDuplicate(String username) throws Exception;
	
	// 회원 정보 수정
	void update(MemberDTO member) throws Exception;
	
	// 회원 삭제
	void delete(String username) throws Exception;
	

}