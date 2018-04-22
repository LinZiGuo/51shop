package com.web.servlet;

import com.model.Goods;
import com.service.GoodsService;
import com.service.impl.GoodsServiceImpl;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public IndexServlet() {
        
    }
    
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		try {
			//获取主页面的热门商品
			GoodsService goodsService = new GoodsServiceImpl();
			List<Goods> hitGoods = goodsService.findHitGoods();
			List<Goods> newGoods = goodsService.findNewGoods();
			List<Goods> nowGoods = goodsService.findNowGoods();
			
			request.getSession().setAttribute("hitGoods", hitGoods);
			request.getSession().setAttribute("newGoods", newGoods);
			request.getSession().setAttribute("nowGoods", nowGoods);
			
			request.getRequestDispatcher("/front/index.jsp").forward(request, response);			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
	}
}
