package com.gguek.app.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.account.dto.AccountDTO;

@Mapper
public interface AccountMapper {
	
	public int create(AccountDTO accountDTO)throws Exception;

}