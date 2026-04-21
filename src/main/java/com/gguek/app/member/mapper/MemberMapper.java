package com.gguek.app.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.member.dto.ProfileDTO;

@Mapper
public interface MemberMapper {

	// INSERT: 회원 가입
	int join(MemberDTO memberDTO) throws Exception;

	// INSERT: 프로필 추가
	int addProfile(ProfileDTO profileDTO) throws Exception;

	// SELECT: 회원 조회 (ID로)
	MemberDTO detail(MemberDTO memberDTO) throws Exception;

	// SELECT: 중복 확인
	MemberDTO selectOne(String username) throws Exception;

	// UPDATE: 회원 정보 수정
	int update(MemberDTO memberDTO) throws Exception;

	// DELETE: 회원 삭제
	int delete(String username) throws Exception;

}