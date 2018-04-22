package com.web.servlet;

import com.model.Category;
import com.model.Goods;
import com.service.CategoryService;
import com.service.GoodsService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tools.BeanFactory;
import com.tools.UploadUtils;
import com.web.servlet.base.BaseServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * 后台商品模块
 * Servlet implementation class AdminGoodsServlet
 */
@WebServlet("/adminGoods")
public class AdminGoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 展示已上架商品列表
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			GoodsService goodsService = (GoodsService) BeanFactory.getBean("GoodsService");
			List<Goods> list = goodsService.findAll();
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
    	
    	return "/manage/goods/list.jsp";
    }

    public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = categoryService.findList();
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			
		}
    	return "/manage/goods/add.jsp";
    }
    
    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.使用fileupload保存图片和将商品信息放入map中
			//1.1创建map存放商品信息
//			Map<String, Object> map = new HashMap<>();
			
			String goodsname = new String(request.getParameter("goodsname").getBytes("iso-8859-1"), "utf-8");
			Integer hit = Integer.valueOf(request.getParameter("hit"));
			String p = request.getParameter("price");
			p=(p==null?"0.00":p);
			Float price = Float.valueOf(p);
			p = request.getParameter("nowprice");
			p=(p==null?"0.00":p);
			Float nowprice = Float.valueOf(p);
			Integer typeId = Integer.valueOf(request.getParameter("typename"));
			String picture = request.getParameter("picture");
			String introduce = new String(request.getParameter("introduce").getBytes("iso-8859-1"), "utf-8");
//			
//			//1.2创建磁盘文件项工厂（设置临时文件的大小和位置）
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			
//			//1.3创建核心上传对象
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			
//			//1.4解析request
//			List<FileItem> list = upload.parseRequest(request);
//			
//			//1.5遍历list 获取每一个文件项
//			for (FileItem fileItem : list) {
//				//1.6获取name属性
//				String key = fileItem.getFieldName();
//				
//				//1.7判断是否普通的上传组件
//				if (fileItem.isFormField()) {
//					//普通
//					map.put(key, fileItem.getString("utf-8"));
//				} else {
//					//文件
//					//a.获取文件名称
//					String name = fileItem.getName();
//					
//					//b.获取文件真实名称1.JPG
//					String realname = UploadUtils.getRealName(name);
//					
//					//c.获取文件随即名称1234567654.JPG
//					String uuidName = UploadUtils.getUUIDName(realname);
//					
//					//d.获取随机目录/a/3
//					String dir = UploadUtils.getDir(uuidName);
//					
//					//e.获取文件内容（输入流）
//					InputStream inputStream = fileItem.getInputStream();
//					
//					//f.创建输出流
//					//获取goods目录的真实路径
//					String goodsPath = getServletContext().getRealPath("/goods");
//					
//					//创建随机目录
//					File dirFile = new File(goodsPath, dir);
//					if (!dirFile.exists()) {
//						dirFile.mkdirs();
//					}
//					
//					FileOutputStream outputStream = new FileOutputStream(new File(dirFile, uuidName));
//					
//					//g.对拷流
//					IOUtils.copy(inputStream, outputStream);
//					
//					//h.释放资源
//					outputStream.close();
//					inputStream.close();
//					
//					//i.删除临时文件
//					fileItem.delete();
//					
//					//j.将商品路径放入map中
//					map.put(key, "goods"+dir+"/"+uuidName);
//				}
//			}
//			
//			//2.封装goods对象
			Goods goods = new Goods();
			goods.setGoodsname(goodsname);
			goods.setHit(hit);
			goods.setIntime(new Date());
			goods.setIntroduce(introduce);
			goods.setNewgoods(1);
			goods.setNowprice(nowprice);
			goods.setPrice(price);
			goods.setPicture(picture);
			goods.setSale(1);
//			map.put("goodsname", goodsname);
//			map.put("price", price);
//			map.put("nowprice", nowprice);
//			map.put("picture", picture);
//			map.put("introduce", introduce);
//			map.put("hit", hit);
//			map.put("intime", new Date());
//			map.put("newgoods", 1);
//			map.put("sale", 1);
			
//			BeanUtils.populate(goods, map);
			
			//3.调用Service完成保存
			GoodsService goodsService = (GoodsService) BeanFactory.getBean("GoodsService");
			goodsService.save(goods,typeId);
			
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/adminGoods?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存商品信息失败");
		}
    	return super.index(request, response);
    }
}
