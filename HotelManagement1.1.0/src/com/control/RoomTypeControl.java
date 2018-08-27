package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoomTypeDAO;
import com.vo.DataClass;
import com.vo.RoomType;

/**
 * Servlet implementation class RoomTypeControl
 */
@WebServlet("/RoomTypeControl")
public class RoomTypeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTypeControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    PrintWriter out;
    RoomTypeDAO dao=new RoomTypeDAO();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        out=response.getWriter();
        String action=request.getParameter("action");
        
        
        if(action.equals("getPager")){
            int page=Integer.parseInt(request.getParameter("page"));
            int limit=Integer.parseInt(request.getParameter("limit")); 
            out.print(DataClass.ok(dao.getCount(), dao.getPager(1, dao.getCount())).toJson());        
        }else if(action.equals("insert")){
            String typeName=request.getParameter("typeName");
            int typePrice=Integer.parseInt(request.getParameter("typePrice"));
            int typeDeposit=Integer.parseInt(request.getParameter("typeDeposit"));//typeRemark
            String typeTimg=request.getParameter("typeTimg");
            String typeRemark=request.getParameter("typeRemark");
            dao.insert(new RoomType(typeName, typePrice, typeDeposit,typeTimg,typeRemark));
            response.getWriter().write("{\"msg\":\"添加成功\"}");
        }else if (action.equals("del")) { //删除用户
			int typeId=Integer.parseInt(request.getParameter("typeId"));
			dao.delete(typeId);
			response.getWriter().print("{\"msg\":\"删除成功\"}");
		}else if(action.equals("update")){
			int typeId=Integer.parseInt(request.getParameter("typeId"));
			String typeName=request.getParameter("typeName");
            int typePrice=Integer.parseInt(request.getParameter("typePrice"));
            int typeDeposit=Integer.parseInt(request.getParameter("typeDeposit"));//typeRemark
            String typeTimg=request.getParameter("typeTimg");
            //System.out.println(typeId+typeName+typePrice+typeDeposit+typeTimg);
            String typeRemark=request.getParameter("typeRemark");
            dao.updatePwd(typeId,typeName, typePrice, typeDeposit,typeTimg,typeRemark);
           
            response.getWriter().print("{\"msg\":\"修改成功\"}");
        }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
