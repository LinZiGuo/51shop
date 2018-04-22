package com.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.beanutils.BeanUtils;

import com.constant.Constant;
import com.model.Member;
import com.service.MemberService;
import com.service.impl.MemberServiceImpl;
import com.web.servlet.base.BaseServlet;

/**
 * 会员模块
 */
@WebServlet("/member")
public class MemberServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		return "/front/login.jsp";
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		try {
			//1.获取用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String checkCode = request.getParameter("checkCode");
			String randCheckCode = request.getSession().getAttribute("randCheckCode").toString();
			
			//2.调用service完成登录 返回值：member
			MemberService memberService = new MemberServiceImpl();
			Member member = memberService.login(username, password);
			
			//3.判断member 根据结果生成提示
			if(member.getUsername() == null) {
				//用户名和密码不匹配
				request.setAttribute("msg", "用户名和密码不匹配");
				return "/front/login.jsp";
			}
			
			//4.判断验证码  根据结果生成提示
			if (!checkCode.equals(randCheckCode)) {
				request.setAttribute("msg", "验证码不正确");
				return "/front/login.jsp";
			}
			
			//登录成功 保存用户登录状态
			request.getSession().setAttribute("member", member);
			
			//判断是否勾选了记住用户名
			if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
				Cookie cookie = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
				cookie.setMaxAge(60*60*24*7);
				cookie.setPath(request.getContextPath()+"/");
				response.addCookie(cookie);
			}
			
			//跳转到index.jsp
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		return "/front/register.jsp";
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		try {
			//1.封装对象
			Member member = new Member();
			BeanUtils.populate(member, request.getParameterMap());
			//2.调用Service完成注册
			MemberService memberService = new MemberServiceImpl();
			int temp = memberService.register(member);
			//3.页面转发提示信息
			if (temp == Constant.USER_EXISTED) {
				System.out.println("已存在");
				request.setAttribute("msg", "该用户已存在");
			}else {
				request.getSession().setAttribute("member", member);
				request.setAttribute("msg", "用户注册成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//转发到msg.jsp
			request.setAttribute("msg", "用户注册失败");
			return "/front/msg.jsp";
		}
		
		return "/front/msg.jsp";
	}

	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
