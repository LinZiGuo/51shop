package com.service.impl;

import com.constant.Constant;
import com.dao.MemberDao;
import com.dao.impl.MemberDaoImpl;
import com.model.Member;
import com.service.MemberService;
import com.tools.MailUtils;

public class MemberServiceImpl implements MemberService {

	/**
	 * 用户注册
	 */
	@Override
	public int register(Member member) throws Exception {
		//1.调用DAO完成注册
		MemberDao memberDao = new MemberDaoImpl();
		//1.1判断能否注册
		if (memberDao.select(member).size()>0) {
			return Constant.USER_EXISTED;
		}
		memberDao.save(member);
		return Constant.REGISTER_SUCCESS;
		//2.发送邮件
//		String emailMsg="恭喜"+member.getTruename()+":成为我们商城的一员！<a href='http://localhost/shop/member?method=active&code="+member.getCode()+"'>点此激活</a>";
//		MailUtils.sendMail(member.getEmail(), emailMsg);
		
	}

	/**
	 * 用户登录
	 */
	@Override
	public Member login(String username, String password) throws Exception {
		MemberDao memberDao = new MemberDaoImpl();
		
		return memberDao.getByUsernameAndPwd(username, password);
	}

}
