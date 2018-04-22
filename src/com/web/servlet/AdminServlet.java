package com.web.servlet;

import com.constant.Constant;
import com.model.Manager;
import com.model.Member;
import com.service.AdminService;
import com.service.MemberService;
import com.service.impl.MemberServiceImpl;
import com.tools.BeanFactory;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

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
			AdminService adminService = (AdminService) BeanFactory.getBean("AdminService");
			Manager manager = adminService.login(username, password);
			
			//3.判断member 根据结果生成提示
			if(manager.getManager() == null) {
				//用户名和密码不匹配
				request.setAttribute("msg", "用户名和密码不匹配");
				return "/front/msg.jsp";
			}
			
			//4.判断验证码  根据结果生成提示
			if (!checkCode.equals(randCheckCode)) {
				request.setAttribute("msg", "验证码不正确");
				return "/front/msg.jsp";
			}
			
			//登录成功 保存用户登录状态
			request.getSession().setAttribute("manager", manager);
			
			//判断是否勾选了记住用户名
			if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
				Cookie cookie = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
				cookie.setMaxAge(60*60*24*7);
				cookie.setPath(request.getContextPath()+"/");
				response.addCookie(cookie);
			}
			//跳转到index.jsp
			response.sendRedirect(request.getContextPath()+"/manage/home.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
