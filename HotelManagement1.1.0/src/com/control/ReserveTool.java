package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.*;
import com.dao.*;
import com.util.JsonUtil;
import com.vo.*;

/**
 * Servlet implementation class ReserveTool
 */
@WebServlet("/ReserveTool")
public class ReserveTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RoomReserveDao reserve_dao=new RoomReserveDao();
	private RoomReserveBo reserve_bo=new RoomReserveBo();
	private RoomDao room_dao=new RoomDao();
	private RoomBo room_bo=new RoomBo();
	private CustomerInfoBo cti_bo=new CustomerInfoBo();
	private CustomerInfoDao cti_dao=new CustomerInfoDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		ArrayList<Room> roomlist=new ArrayList<Room>();
		if(action.equals("getCheckIn")) {
			if(room_bo.findByAvailable(0)) {
				if(room_bo.findByState(0)) {//参数0代表房间的状态
					roomlist=room_dao.findByState(0);//查找所有可入住的房间
					out.println(JsonUtil.toJson(roomlist));
				}
			}
		}else if(action.equals("getCustomer")) {
			///System.out.print(request.getParameter("customerId"));
			int customerId=Integer.parseInt(request.getParameter("customerId"));
			if(cti_bo.judgeVIP(customerId)) {
				CustomerInfo cti=cti_dao.judgeVIP(customerId);
				out.println(JsonUtil.toJson(cti));
			}else {
				out.write("{\"msg\":\"Sorry,该用户不是VIP!\"}");
			}
		}else if(action.equals("addReserve")) {
			String roomNum=request.getParameter("roomNum");
			int empId=1;//Integer.parseInt(request.getParameter("empId"));
			String reserveTime=request.getParameter("reserveTime");
			String customerName=request.getParameter("customerName");
			String customerPhone=request.getParameter("customerPhone");
			String reserveRemark=request.getParameter("reserveRemark");
			if(reserve_bo.insertRoomReserve(roomNum, empId, reserveTime, customerName, customerPhone, reserveRemark)) {
				out.write("{\"msg\":\"预定成功\"}");
				System.out.println("预定成功");
			}else {
				out.write("{\"msg\":\"预定失败,请联系管理员！\"}");
			}
		}else if(action.equals("delReserve")) {
			String roomNum=request.getParameter("roomNum");
			if(room_bo.updateRoomState(roomNum, 0)) {
				out.write("{\"msg\":\"退订成功\"}");
			}
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
