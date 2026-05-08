package com.gguek.app.member.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO implements UserDetails{

	@NotBlank(groups = GroupAdd.class)
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,11}$")
	private String username;

	@NotBlank(groups = {GroupAdd.class, GroupAdd.class})
	private String name;

	@NotBlank(groups = GroupAdd.class)
	@Size(max = 10, min = 4, groups = GroupAdd.class)
//	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$", 
//	         message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~16자여야 합니다.")
	private String password;

	@NotBlank(groups = GroupAdd.class)
	private String passwordCheck;
	
	private String phone;

	@Email(groups = {GroupAdd.class, GroupAdd.class})
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd") // 💡 이 설정이 없으면 input type="date"가 인식 못 함
	@Past(message = "생년월일은 과거 날짜여야 합니다.", groups = {GroupAdd.class, GroupAdd.class})
	private LocalDate birth;
	
	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean enabled;

	private ProfileDTO profileDTO;

	private List<RoleDTO> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> ar = new ArrayList<>();
		for(RoleDTO roleDTO:this.roles){
			GrantedAuthority g = new SimpleGrantedAuthority(roleDTO.getRoleName());
			ar.add(g);
		}

		return ar;
	}

	



	
}
