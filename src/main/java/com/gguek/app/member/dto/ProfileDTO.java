package com.gguek.app.member.dto;

import com.gguek.app.file.dto.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileDTO extends FileDTO{
	
	private String username;
}
