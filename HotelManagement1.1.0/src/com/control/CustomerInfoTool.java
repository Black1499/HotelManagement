package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CustomerInfoDao;
import com.util.JsonUtil;
import com.vo.CustomerInfoVo;
import com.vo.DataClass;

/**
 * Servlet implementation class CustomerInfoTool
 */
@WebServlet("/CustomerInfoTool")
public class CustomerInfoTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInfoTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		CustomerInfoDao cid=new CustomerInfoDao();
		if(action.equals("getPage")) {
			PrintWriter out=response.getWriter();
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			List<CustomerInfoVo> list=cid.getCtiAll();
			int count=list.size();
			//System.out.print(JsonUtil.toJson(cid.getPager(page, limit)));
			out.println(DataClass.ok(count,cid.getPager(page, limit)).toJson());
		}else if(action.equals("getVIP")) {
			PrintWriter out=response.getWriter();
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			List<CustomerInfoVo> list=cid.getCtiAll();
			int count=list.size();
			out.println(DataClass.ok(count,cid.getCit(page, limit, 1)).toJson());
		}else if(action.equals("getCut")) {
			PrintWriter out=response.getWriter();
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			List<CustomerInfoVo> list=cid.getCtiAll();
			int count=list.size();
			out.println(DataClass.ok(count,cid.getCit(page, limit, 0)).toJson());
		}else if(action.equals("getCutName")) {
			PrintWriter out=response.getWriter();
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String name=request.getParameter("name");
			List<CustomerInfoVo> list=cid.getCtiAll();
			int count=list.size();
			out.println(DataClass.ok(count,cid.getCitByName(page, limit, name)).toJson());
		}else if(action.equals("getCutNameOut")) {
			String name=request.getParameter("name");
			List<CustomerInfoVo> list=cid.getCitByName(1, 100, name);
			outCutomer(response,list);
		}
		
	}

	private void outCutomer(HttpServletResponse response,List<CustomerInfoVo> list) throws UnsupportedEncodingException, IOException {
		  response.setCharacterEncoding("utf-8");
		// 写入bom头
		byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
   
		String excelName=URLEncoder.encode("客户信息.csv","utf-8"); 
		
		//修改http头部，设置输出为附件
		response.setHeader("Content-Disposition", "attachment;filename="+excelName);
		
		String result="客户编号,客户名字,客户电话,证件号码,是否VIP,入住次数,客户备注\r\n";
		CustomerInfoVo cif=null;
		for(int i=0;i<list.size();i++) {
			cif=list.get(i);
			result+=cif.getCustomerId()+","+cif.getCustomerName()+","+cif.getCustomerPhone()+";,"+cif.getCustomerIdNum()+";,"
			+cif.getCustomerVIP()+","+cif.getCustomerCount()+","+cif.getCustomerRemark()+"\r\n";
		}
		//result=new String(result.getBytes(),"utf-8");
		//将字节流写入response中
		response.getOutputStream().write(uft8bom);
		response.getOutputStream().write(result.getBytes("utf-8"));
		response.flushBuffer();
		response.getOutputStream().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
