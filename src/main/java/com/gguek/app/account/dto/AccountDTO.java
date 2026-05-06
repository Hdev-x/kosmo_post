package com.gguek.app.account.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
	
	private String accountNum;
	private String username;
	private Long productNum;
	private Long accountPw;
	private Long accountBalance;
	private LocalDate accountDate;

}