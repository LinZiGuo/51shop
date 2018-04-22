package com.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Member;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
@WebFilter("/PrivilegeFilter")
public class PrivilegeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1.强转
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		//2.逻辑
		//从session中获取用户
		Member member = (Member) httpServletRequest.getSession().getAttribute("member");
		if (member == null) {
			//未登录
			httpServletRequest.setAttribute("msg", "请先登录");
			httpServletRequest.getRequestDispatcher("/front/msg.jsp").forward(httpServletRequest, httpServletResponse);
			return ;
		}
		
		//3.放行
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
