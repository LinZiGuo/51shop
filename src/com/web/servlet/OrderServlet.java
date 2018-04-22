package com.web.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.constant.Constant;
import com.model.Cart;
import com.model.CartItem;
import com.model.Member;
import com.model.Order;
import com.model.OrderItem;
import com.model.PageBean;
import com.service.OrderService;
import com.tools.BeanFactory;
import com.tools.JsonUtil;
import com.tools.UUIDUtils;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单模块
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 保存订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//-1.从session中获取user
    		String recevieName = request.getParameter("recevieName");
    		String address = request.getParameter("address");
    		String tel = request.getParameter("tel");
    		String bz = request.getParameter("bz");
    		String p = request.getParameter("price");
    		Float price = Float.valueOf(p);
    		Float subTotal = 0.0f;
    		
			Member member = (Member) request.getSession().getAttribute("member");
			if (member == null) {
				//未登录
				request.setAttribute("msg", "请先登录");
				return "/front/msg.jsp";
			}
			//0.获取购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			// 1.封装订单
			//1.1创建对象
			Order order = new Order();
			
			//1.2设置属性
			order.setOrderID(UUIDUtils.getId());
			order.setReceiveName(recevieName);
			order.setTel(tel);
			order.setBeizhu(bz);
			order.setAddress(address);
			order.setOrderDate(new Date());
			order.setState(Constant.ORDER_WEIFUKUAN);
			order.setMember(member);
			//1.3设置items(订单项列表） 遍历购物项列表
			for (CartItem ci : cart.getCartItems()) {
				//1.3.1封装成orderitem
				//a.创建orderitem
				OrderItem orderItem = new OrderItem();
				//b.设置属性
				orderItem.setSubtotal(price);
				orderItem.setCount(ci.getCount());
				orderItem.setGoods(ci.getGoods());
				orderItem.setOrder(order);
				subTotal += orderItem.getSubtotal();
				
				//1.3.2将每一个orderitem加入order的items中
				order.getItems().add(orderItem);
			}
			order.setSubTotal(subTotal);
			
			//2.调用orderService 完成保存操作
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			orderService.save(order);
			
			//3.请求转发到orderDetail.jsp
			request.setAttribute("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "/front/orderDetail.jsp";
    }

    /**
     * 我的订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获取pageNumber 设置pagesize 获取memberid
			int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
			int pagesize=3;
			
			Member member = (Member) request.getSession().getAttribute("member");
			if (member == null) {
				//未登录
				request.setAttribute("msg", "请先登录");
				return "/front/msg.jsp";
			}
			
			//2.调用service 获取当前页所有数据 pagebean
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			PageBean<Order> bean = orderService.findMyOrdersByPage(pageNumber,pagesize,member.getUsername());
			
			//3.将pagebean放入request域中，请求转发order_list.jsp
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "获取我的订单失败");
			return "/front/msg.jsp";
		}
    	
    	return "/front/order_list.jsp";
    }
    
    /**
     * 获取订单详情
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获取orderID
			String id = request.getParameter("ID");
			
			//2.调用Service 查询单个订单
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			Order order = orderService.getById(id);
			
			//3.请求转发
			request.setAttribute("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "获取订单详情失败");
			return "/front/msg.jsp";
		}
    	
    	return "/front/orderDetail.jsp";
    }
    
    /**
     * 在线支付
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获取收货信息 获取orderID 获取支付方式
			
			//2.调用service 获取订单 修改收货人信息 重新订单
			
			//3.拼接给第三方的url
			
			//4.重定向
			
			
			//接收参数
			String address=request.getParameter("address");
			String receiveName=request.getParameter("recevieName");
			String tel=request.getParameter("tel");
			String bz=request.getParameter("bz");
			String orderID=request.getParameter("orderID");
			String subTotal=request.getParameter("subTotal");
			
			//通过ID获取order
			OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
			Order order = orderService.getById(orderID);
			
			order.setAddress(address);
			order.setReceiveName(receiveName);
			order.setTel(tel);
			order.setBeizhu(bz);
			
			//更新order
			orderService.updateOrder(order);
			
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, 
					AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
					AlipayConfig.FORMAT,AlipayConfig.CHARSET,
					AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
			
			//创建API对应的request
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			
			//在公共参数中设置回跳和通知地址
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = order.getOrderID();
			//付款金额，必填
			String total_amount = subTotal;
			//订单名称，必填
			String subject = "51Mall";
			//商品描述，可空
			String body = "51Mallbody";
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
	                + "\"total_amount\":\""+ total_amount +"\"," 
	                + "\"subject\":\""+ subject +"\"," 
	                + "\"body\":\""+ body +"\"," 
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//请求
	        String result = alipayClient.pageExecute(alipayRequest).getBody();
	        
	        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
	        response.getWriter().write(result);
	        response.getWriter().flush();
	        response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 支付成功之后的回调函数
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//获取支付宝GET过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			params.put("method", "callback");
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
			
			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//该页面可做页面美工编辑
				//out.clear();
				//out.println("验证成功<br />");
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）
				request.setAttribute("msg", "订单支付成功");
				return "/front/msg.jsp";				
				
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{
				//该页面可做页面美工编辑
				//out.clear();
				//out.println("验证失败");
				request.setAttribute("msg", "订单支付成功");
				return "/front/msg.jsp";
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "订单支付失败");
			return "/front/msg.jsp";
		}
    }
}
