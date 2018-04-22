package com.web.servlet;

import com.model.Category;
import com.service.CategoryService;
import com.tools.BeanFactory;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台分类管理模块
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/adminCategory")
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 展示所有分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = categoryService.findList();
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
    	
    	return "/manage/category/list.jsp";
    }

	/**
	 * 跳转到添加分类页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/manage/category/add.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Category category = new Category();
			String typename = new String(request.getParameter("typename").getBytes("iso-8859-1"), "utf-8");
			category.setTypename(typename);
			CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
			categoryService.save(category);
			
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return null;
	}
}
