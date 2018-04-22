package com.web.servlet;

import com.constant.Constant;
import com.model.Order;
import com.model.OrderItem;
import com.service.OrderService;
import com.tools.BeanFactory;
import com.tools.JsonUtil;
import com.web.servlet.base.BaseServlet;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台订单模块
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/adminOrder")
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			String state = request.getParameter("state");
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			List<Order> list = orderService.findAllByState(state);
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "/manage/order/list.jsp";
    }

    /**
     * 展示订单详情
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		//0.设置编码
    		response.setContentType("text/html;charset=utf-8");
    		
			//1.获取orderID
			String orderID = request.getParameter("orderID");
			
			//2.调用Service 获取订单
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			Order order = orderService.getById(orderID);
			
			//3.获取订单的订单项列表 转成json 写回浏览器
			if (order!=null) {
				List<OrderItem> list = order.getItems();
				if (list!=null&&list.size()>0) {
//					response.getWriter().println(JsonUtil.list2json(list));
					
					JsonConfig jsonConfig = JsonUtil.configJson(new String[] {"ID","hit","intime",
							"introduce","newgoods","nowprice","picture",
							"sale","typename","itemid","order"});
					response.getWriter().println(JSONArray.fromObject(list, jsonConfig));
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获取orderID
			String orderID = request.getParameter("orderID");
			
			//2.调用Service 获取订单
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			Order order = orderService.getById(orderID);
			
			//3.设置订单的状态，更新
			order.setState(Constant.ORDER_YIFAHUO);
			orderService.updateOrder(order);
			
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
		} catch (Exception e) {
			
		}
    	return null;
    }
}
