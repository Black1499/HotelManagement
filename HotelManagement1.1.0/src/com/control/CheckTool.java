package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.CheckOutBo;
import com.bo.CustomerInfoBo;
import com.bo.RoomCheckBo;
import com.dao.CheckOutDao;
import com.dao.CustomerInfoDao;
import com.dao.RoomCheckDao;
import com.dao.RoomDao;
import com.util.JsonUtil;
import com.vo.CheckOut;
import com.vo.CustomerInfo;

/**
 * Servlet implementation class CheckTool
 */
@WebServlet("/CheckTool")
public class CheckTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RoomCheckDao check_dao=new RoomCheckDao();
    private RoomCheckBo check_bo=new RoomCheckBo();
    private CustomerInfoBo cti_bo=new CustomerInfoBo();
	private CustomerInfoDao cti_dao=new CustomerInfoDao();
	private RoomDao room_dao=new RoomDao();
	private CheckOutDao co_dao=new CheckOutDao();
	private CheckOutBo co_bo=new CheckOutBo();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		int empId=1;//操作员编号
		if(action.equals("addCheck")) {
			addCheck(request, out, empId);
		}else if(action.equals("vipCheck")) {
			vipCheck(request, out, empId);
		}else if(action.equals("updCheck")) {
			updCheck(request, out, empId);
		}else if(action.equals("getCut")) {
			String name=request.getParameter("name");
			if(cti_bo.findByName(name)) {
				out.write(JsonUtil.toJson(cti_dao.findByName(name)));
			}
		}
	}
	/**
	 * 当入住的房间改变时,通过原房间号获得outId,再根据outId进行修改
	 * */
	public void updOut(String initNum,String roomNum,boolean bool) {
		CheckOut co=null;
		System.out.println(initNum);
		if(co_bo.findByNum(initNum)) {
			co=co_dao.findByNum(initNum);
		}
		//System.out.println(co.getOutId());
		int outId=co.getOutId();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate=sdf.format(new Date());//当前时间
		int price=room_dao.findByNum(initNum).getTypePrice();//原房间价格
		String checkDate=check_dao.findByNum(roomNum).getCheckTime();//原房间入住时间
		int insertPrice=getOutPrice(nowDate,checkDate,price);//原房间需交纳的金额
		//System.out.println(roomNum+"==="+nowDate+"==="+checkDate+"====="+insertPrice);
		if(bool==true) {
			String remark="该用户更换过房间,原房间号是"+initNum+",原房间住宿金额："+insertPrice;
			if(co_bo.updCheckOut(roomNum, nowDate, insertPrice,remark, outId)) {
				System.out.println("结账表修改成功");
			}
		}else {
			String remark="该用户更换过房间，原房间号是"+initNum;
			if(co_bo.updCheckOut(roomNum, nowDate, 0, remark,outId)) {
				System.out.println("结账表修改成功");
			}
		}
		
	}
	/**
	 * 当客户入住时，变换入住房间，计算出当前消费金额
	 * */
	public int getOutPrice(String nowDate,String checkDate,int price) {
		int inow=Integer.parseInt(nowDate.substring(8, 10));//当前日期
		int icheck=Integer.parseInt(checkDate.substring(8, 10));//以后的日期
		String month=checkDate.substring(5,7);//月份
		int insertPrice=0;
		//18入住，19号离开，31号入住，2号离开，当天入住，当天离开
		if(inow>icheck) {
			insertPrice=price*(inow-icheck);//结账价格
		}else if(icheck>inow) {
			if(month.equals("01")||month.equals("03")||month.equals("05")||month.equals("07")||month.equals("08")||month.equals("10")||month.equals("12")) {
				insertPrice=price*inow+price*(31-icheck);
			}else if(month.equals("04")||month.equals("06")||month.equals("09")||month.equals("11")) {
				insertPrice=price*inow+price*(30-icheck);
			}else if(month.equals("02")) {
				insertPrice=price*inow+price*(29-icheck);
			}
		}else if(icheck==inow) {
			insertPrice=price;
		}
		return insertPrice;
	}
	/**
	 * 修改入住信息
	 * */
	private void updCheck(HttpServletRequest request, PrintWriter out, int empId) {
		String roomNum=request.getParameter("roomNum");//现房间
		String customerName=request.getParameter("customerName");
		String customerPhone=request.getParameter("customerPhone");
		String customerIdNum=request.getParameter("customerIdNum");
		String checkTime=request.getParameter("checkTime");
		int checkHour=Integer.parseInt(request.getParameter("checkHour"));
		String checkRemark=request.getParameter("checkRemark");
		int checkId=Integer.parseInt(request.getParameter("checkId"));
		String initNum=request.getParameter("initNum");//原房间
		String initsNum=request.getParameter("initsNum");//原房间
		if(check_bo.updateRoomCheck(roomNum, empId, customerName, customerPhone, customerIdNum, checkTime, checkHour, checkRemark, checkId)) {
			room_dao.updateRoomState(roomNum, 1);
			if(initNum!=null) {
				room_dao.updateRoomState(initNum, 3);
				updOut(initNum,roomNum,true);//修改结账表入住的房间
			}
			if(initsNum!=null) {
				room_dao.updateRoomState(initsNum, 0);
				updOut(initsNum,roomNum,false);//修改结账表入住的房间
			}
			out.write("{\"msg\":\"修改成功！\"}");
			System.out.println("修改成功");
		}else {
			out.write("{\"msg\":\"修改失败，请重新尝试！\"}");
		}
	}
	/**
	 * vip入住
	 * */
	private void vipCheck(HttpServletRequest request, PrintWriter out, int empId) {
		int customerId=Integer.parseInt(request.getParameter("vipId"));
		String roomNum=request.getParameter("roomNum");
		String remark=request.getParameter("remark");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(cti_bo.findById(customerId)) {
			CustomerInfo cif=cti_dao.findById(customerId);
			if(check_bo.insertRoomCheck(roomNum, empId, cif.getCustomerName(), cif.getCustomerPhone(), cif.getCustomerIdNum(),df.format(new Date()) , 0,remark )) {
				co_dao.insertOut(roomNum);
				out.write("{\"msg\":\"入住成功！\"}");
				System.out.println("入住成功");
			}else {
				out.write("{\"msg\":\"入住失败，请重新尝试！\"}");
			}
		}
	}
	/**
	 * 普通客户入住
	 * */
	private void addCheck(HttpServletRequest request, PrintWriter out, int empId) {
		String roomNum=request.getParameter("roomNum");
		String customerName=request.getParameter("customerName");
		String customerPhone=request.getParameter("customerPhone");
		String customerIdNum=request.getParameter("customerIdNum");
		String checkTime=request.getParameter("checkTime");
		int checkHour=Integer.parseInt(request.getParameter("checkHour"));
		String checkRemark=request.getParameter("checkRemark");
		if(check_bo.insertRoomCheck(roomNum, empId, customerName, customerPhone, customerIdNum, checkTime, checkHour, checkRemark)) {
			co_dao.insertOut(roomNum);
			if(cti_bo.findByNamePhone(customerName, customerPhone)==false) {
				cti_dao.addCust(customerName, customerIdNum, customerPhone);
			}
			out.write("{\"msg\":\"入住成功！\"}");
			System.out.println("入住成功");
		}else {
			out.write("{\"msg\":\"入住失败，请重新尝试！\"}");
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
