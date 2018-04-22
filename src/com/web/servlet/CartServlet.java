package com.web.servlet;

import com.model.Cart;
import com.model.CartItem;
import com.model.Goods;
import com.service.GoodsService;
import com.tools.BeanFactory;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车模块
 */
@WebServlet("/cart")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public String add2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			// 1.获取ID count
			String id = request.getParameter("ID");
			int count = Integer.valueOf(request.getParameter("count"));
			
			//2.封装cartitem
			//调用Service获取goods
			GoodsService goodsService = (GoodsService) BeanFactory.getBean("GoodsService");
			Goods goods = goodsService.getById(id);
			
			//创建cartitem
			CartItem cartItem = new CartItem(goods, count);
			
			//3.将cartitem加入到购物车
			//获取购物车
			Cart cart = getCart(request);
			
			cart.add2Cart(cartItem);
			
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/front/cart.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "加入购物车失败");
			return "/front/msg.jsp";			
		}
    	
    	return null;
    }

    /**
     * 获取购物车
     * @param request
     * @return
     */
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			
			//将cart放入session中
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 移除购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取商品的ID
		String id = request.getParameter("ID");
		
		//2.获取购物车 执行移除
		getCart(request).removeFromCart(id);
		
		//3.重定向
		response.sendRedirect(request.getContextPath()+"/front/cart.jsp");
		
		return null;
	}

	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取购物车 执行清空操作
		getCart(request).ClearCart();
		
		//2.重定向
		response.sendRedirect(request.getContextPath()+"/front/cart.jsp");
		
		return null;
	}
}
