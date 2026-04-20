package com.gguek.app.member.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

	private String username;
	private String name;
	private String password;
	private String phone;
	private String email;
	private LocalDate birth;
	
	private ProfileDTO profileDTO;
	
}
