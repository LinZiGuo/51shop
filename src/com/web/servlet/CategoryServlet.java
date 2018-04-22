package com.web.servlet;

import com.service.CategoryService;
import com.service.impl.CategoryServiceImpl;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台分类模块
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CategoryServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 查询所有分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//0.设置相应编码
			response.setContentType("text/html;charset=utf-8");
			// 1.调用Service，查询所有分类，返回值json字符串
			CategoryService categoryService = new CategoryServiceImpl();
			//从SQLServer数据库获取列表
			String value = categoryService.findAll();
			
			//从redis中获取列表
//			String value = categoryService.findAllFromRedis();
			//2.将字符串写回浏览器
			response.getWriter().println(value);
		} catch (Exception e) {
			
		}
    	return null;
    }
}
