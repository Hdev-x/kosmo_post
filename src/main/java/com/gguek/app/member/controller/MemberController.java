package com.gguek.app.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.file.manager.FileManager;
import com.gguek.app.member.dto.GroupAdd;
import com.gguek.app.member.dto.GroupUpdate;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.member.dto.ProfileDTO;
import com.gguek.app.member.service.MemberService;
import com.gguek.app.member.service.MemberServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	private final MemberServiceImpl memberServiceImpl;

	@Autowired
	private MemberService memberService;

	@Autowired
	private FileManager fileManager;

	MemberController(MemberServiceImpl memberServiceImpl) {
		this.memberServiceImpl = memberServiceImpl;
	}

	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		log.info("{}", memberDTO);
		model.addAttribute("memberDTO", memberDTO);
		return "member/update";
	}

	@PostMapping("update")
	public String update(@Validated(GroupUpdate.class) MemberDTO memberDTO, BindingResult bindingResult,
			HttpSession session) throws Exception {

		// 1. 유효성 검사 실패 시 처리
		if (bindingResult.hasErrors()) {
			log.warn("검증 에러 발생: {}", bindingResult.getAllErrors());
			return "member/update"; // 에러 메시지를 담고 수정 페이지로 복귀
		}

		// 2. 세션에서 고유 식별자(username) 가져와서 DTO에 세팅
		MemberDTO sessionMember = (MemberDTO) session.getAttribute("member");
		memberDTO.setUsername(sessionMember.getUsername());

		// 3. DB 업데이트 수행
		// memberService.update(memberDTO); // 작성하신 MyBatis update 문이 실행됨
		log.info("DB 업데이트 시도 데이터: {}", memberDTO);

		// 4. 세션 정보 갱신 (핵심!)
		// 수정한 정보(memberDTO)를 다시 세션에 담아야 마이페이지에서 바로 확인 가능합니다.
		// 만약 password가 null로 넘어왔다면 기존 세션의 비밀번호를 유지하는 로직을 서비스에 넣는 것이 좋습니다.
		session.setAttribute("member", memberDTO);

		log.info("회원정보 수정 및 세션 갱신 완료: {}", memberDTO.getUsername());

		// 5. 리다이렉트로 마이페이지 이동 (URL 중복 방지)
		return "redirect:/member/mypage";
	}

	// 마이페이지
	@GetMapping("mypage")
	public String mypage(HttpSession session) throws Exception {
		Object obj = session.getAttribute("member");
		if (obj == null) {
			return "redirect:./login";
		}

		return "member/mypage";
	}

	// 회원가입 페이지
	@GetMapping("join")
	public String join(@ModelAttribute("memberDTO") MemberDTO memberDTO) throws Exception {
		return "member/join";
	}

	// 회원가입 처리
	@PostMapping("join")
	public String joinPost(@Validated(GroupAdd.class) MemberDTO memberDTO, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) throws Exception {

		// 💡 추가: @Valid 검증 결과 에러가 하나라도 있다면 다시 가입 페이지로 리턴
		if (bindingResult.hasErrors()) {
			return "member/join";
		}

		// 서비스 단의 커스텀 검증 (중복 체크 등)
		if (memberService.doubleCheck(memberDTO, bindingResult)) {
			return "member/join";
		}

		// 가입 성공 로직
		// memberService.join(memberDTO, file);
		return "redirect:/member/login";
	}

	// 로그인 페이지
	@GetMapping("login")
	public String login(Model model) throws Exception {
		// 검증이 되지 않은 깨끗한 빈 객체를 생성해서 전달
		model.addAttribute("memberDTO", new MemberDTO());
		return "member/login"; // 명시적으로 뷰 이름을 리턴하는 것이 안전
	}

	// 로그인 처리
	@PostMapping("login")
	public String login(MemberDTO memberDTO, BindingResult bindingResult, HttpSession session, Model model)
			throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "member/login";

		}

		// 예시: 서비스에서 DB 조회를 통해 실제 회원인지 확인
		MemberDTO loginResult = memberService.detail(memberDTO);

		if (loginResult != null) {
			session.setAttribute("member", loginResult); // DB에서 가져온 실제 정보를 세션에 저장
			return "redirect:/";
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "member/login";
		}
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}

}