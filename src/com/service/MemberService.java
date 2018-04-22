package com.service;

import com.model.Member;

public interface MemberService {

	int register(Member member) throws Exception;

	Member login(String username, String password) throws Exception;

}
