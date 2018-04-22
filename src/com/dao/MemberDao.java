package com.dao;

import java.util.List;

import com.model.Member;

public interface MemberDao {

	public int insert(Member member);
	
	public List<Member> select();

	public void save(Member member) throws Exception;

	public Member getByUsernameAndPwd(String username, String password) throws Exception;

	public List<Member> select(Member member);
}
