package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Goods;
import com.model.PageBean;
import com.service.GoodsService;
import com.service.impl.GoodsServiceImpl;
import com.tools.JsonUtil;
import com.web.servlet.base.BaseServlet;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/goods")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取商品ID
			String ID = request.getParameter("ID");
			
			if(Integer.valueOf(ID)<=0) {
				request.setAttribute("msg", "您的操作有误");
				return "/front/msg.jsp";
			}
			
			//2.调用Service获取单个商品  参数:ID 返回值:Goods
			GoodsService goodsService = new GoodsServiceImpl();
			Goods goods = goodsService.getById(ID);
			
			//3.将Goods放入request域中，请求转发/front/goodsDetail.jsp
			request.setAttribute("goods", goods);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询商品详情失败");
			return "/front/msg.jsp";
		}
		
		return "/front/goodsDetail.jsp";
	}
	
	public String getRelatedGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String typename = request.getParameter("typename");
			System.out.println("********typename="+typename);
			
			GoodsService goodsService = new GoodsServiceImpl();
			List<Goods> related = goodsService.getRelatedGoods(typename);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pagenumber ID 设置pagesize
			int pageNumber = 1;
			
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
			
			int pageSize = 12;
			String ID = request.getParameter("ID");
			
			//2.调用Service 分页查询商品 参数：3个
			GoodsService goodsService = new GoodsServiceImpl();
			PageBean<Goods> pageBean = goodsService.findByPage(pageNumber,pageSize,ID);
			System.out.println("**********pageBean pageNumber="+pageBean.getPageNumber());
			System.out.println("**********pageSize="+pageBean.getPageSize());
			System.out.println("**********totalRecord="+pageBean.getTotalRecord());
			System.out.println("**********totalPage="+pageBean.getTotalPage());
			System.out.println("**********data="+JsonUtil.list2json(pageBean.getData()));
			
			//3.将pagebean放入request中 请求转发到/front/goods_list.jsp
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "分页查询失败");
			return "/front/msg.jsp";
		}
		
		return "/front/goods_list.jsp";
	}
}
