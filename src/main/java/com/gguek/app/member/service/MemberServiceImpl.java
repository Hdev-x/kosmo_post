package com.gguek.app.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.file.manager.FileManager;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.member.dto.ProfileDTO;
import com.gguek.app.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;

	@Value("${app.member}")
	private String name;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 시도 아이디: " + username);

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUsername(username);

		// 1. DB에서 회원 정보 가져오기
		memberDTO = memberMapper.detail(memberDTO);

		// 2. 만약 없는 아이디라면 예외를 던져줘야 함 (시큐리티 약속)
		if (memberDTO == null) {
			log.warn("해당 사용자를 찾을 수 없습니다: {}", username);
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
		}

		log.info("조회된 회원 정보: {}", memberDTO);

		// 3. 이미 조회된 memberDTO를 그대로 반환!
		return memberDTO;
	}

	// 사용자 정의 검증 메서드
	public boolean doubleCheck(MemberDTO memberDTO, BindingResult bindingResult) throws Exception {
		// false : 검증 통과
		// true : 검증 실패
		boolean result = false;

		// annotation으로 검증한 결과 담기
		result = bindingResult.hasErrors();

		// password 일치 검증
		if (!memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {
			bindingResult.rejectValue("passwordCheck", "member.passwordCheck.notEqual");
			result = true;
		}

		// ID 중복 검사
		MemberDTO m = memberMapper.detail(memberDTO);
		if (m != null) {
			result = true;
			bindingResult.rejectValue("username", "member.user.equal");

		}

		return result;
	}

	// 회원 가입
	@Override
	public int join(MemberDTO memberDTO, MultipartFile file) throws Exception {
		// DB에 저장
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
		int result = memberMapper.join(memberDTO);
		// profile이미지를 HDD에 저장
		if (file != null && !file.isEmpty()) {
			String fileName = fileManager.fileSave(name, file);
			// 저장된 정보를 DB에 저장
			ProfileDTO profileDTO = new ProfileDTO();
			profileDTO.setFileName(fileName);
			profileDTO.setOriginName(file.getOriginalFilename());
			profileDTO.setUsername(memberDTO.getUsername());
			result = memberMapper.addProfile(profileDTO);
		}

		return result;
	}

	// 로그인 (회원 조회)
	@Override
	public MemberDTO detail(MemberDTO memberDTO) throws Exception {
		MemberDTO check = memberMapper.detail(memberDTO);
		if (check != null) {
			if (check.getPassword().equals(memberDTO.getPassword())) {
				return check;
			}
		}
		return null;
	}

	// 아이디 중복 확인
	@Override
	public boolean isDuplicate(String username) throws Exception {
		return memberMapper.selectOne(username) != null;
	}

	// 회원 정보 수정
	@Override
	public int update(MemberDTO memberDTO) throws Exception {
		int result = memberMapper.update(memberDTO);
		return result;
	}

	// 회원 삭제
	@Override
	public int delete(String username) throws Exception {
		int result = memberMapper.delete(username);
		return result;
	}

	// 프로필 추가
	@Override
	public void addProfile(ProfileDTO profile) throws Exception {
		memberMapper.addProfile(profile);
	}

}