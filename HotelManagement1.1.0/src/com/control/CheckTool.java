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
		int empId=1;//����Ա���
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
	 * ����ס�ķ���ı�ʱ,ͨ��ԭ����Ż��outId,�ٸ���outId�����޸�
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
		String nowDate=sdf.format(new Date());//��ǰʱ��
		int price=room_dao.findByNum(initNum).getTypePrice();//ԭ����۸�
		String checkDate=check_dao.findByNum(roomNum).getCheckTime();//ԭ������סʱ��
		int insertPrice=getOutPrice(nowDate,checkDate,price);//ԭ�����轻�ɵĽ��
		//System.out.println(roomNum+"==="+nowDate+"==="+checkDate+"====="+insertPrice);
		if(bool==true) {
			String remark="���û�����������,ԭ�������"+initNum+",ԭ����ס�޽�"+insertPrice;
			if(co_bo.updCheckOut(roomNum, nowDate, insertPrice,remark, outId)) {
				System.out.println("���˱��޸ĳɹ�");
			}
		}else {
			String remark="���û����������䣬ԭ�������"+initNum;
			if(co_bo.updCheckOut(roomNum, nowDate, 0, remark,outId)) {
				System.out.println("���˱��޸ĳɹ�");
			}
		}
		
	}
	/**
	 * ���ͻ���סʱ���任��ס���䣬�������ǰ���ѽ��
	 * */
	public int getOutPrice(String nowDate,String checkDate,int price) {
		int inow=Integer.parseInt(nowDate.substring(8, 10));//��ǰ����
		int icheck=Integer.parseInt(checkDate.substring(8, 10));//�Ժ������
		String month=checkDate.substring(5,7);//�·�
		int insertPrice=0;
		//18��ס��19���뿪��31����ס��2���뿪��������ס�������뿪
		if(inow>icheck) {
			insertPrice=price*(inow-icheck);//���˼۸�
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
	 * �޸���ס��Ϣ
	 * */
	private void updCheck(HttpServletRequest request, PrintWriter out, int empId) {
		String roomNum=request.getParameter("roomNum");//�ַ���
		String customerName=request.getParameter("customerName");
		String customerPhone=request.getParameter("customerPhone");
		String customerIdNum=request.getParameter("customerIdNum");
		String checkTime=request.getParameter("checkTime");
		int checkHour=Integer.parseInt(request.getParameter("checkHour"));
		String checkRemark=request.getParameter("checkRemark");
		int checkId=Integer.parseInt(request.getParameter("checkId"));
		String initNum=request.getParameter("initNum");//ԭ����
		String initsNum=request.getParameter("initsNum");//ԭ����
		if(check_bo.updateRoomCheck(roomNum, empId, customerName, customerPhone, customerIdNum, checkTime, checkHour, checkRemark, checkId)) {
			room_dao.updateRoomState(roomNum, 1);
			if(initNum!=null) {
				room_dao.updateRoomState(initNum, 3);
				updOut(initNum,roomNum,true);//�޸Ľ��˱���ס�ķ���
			}
			if(initsNum!=null) {
				room_dao.updateRoomState(initsNum, 0);
				updOut(initsNum,roomNum,false);//�޸Ľ��˱���ס�ķ���
			}
			out.write("{\"msg\":\"�޸ĳɹ���\"}");
			System.out.println("�޸ĳɹ�");
		}else {
			out.write("{\"msg\":\"�޸�ʧ�ܣ������³��ԣ�\"}");
		}
	}
	/**
	 * vip��ס
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
				out.write("{\"msg\":\"��ס�ɹ���\"}");
				System.out.println("��ס�ɹ�");
			}else {
				out.write("{\"msg\":\"��סʧ�ܣ������³��ԣ�\"}");
			}
		}
	}
	/**
	 * ��ͨ�ͻ���ס
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
			out.write("{\"msg\":\"��ס�ɹ���\"}");
			System.out.println("��ס�ɹ�");
		}else {
			out.write("{\"msg\":\"��סʧ�ܣ������³��ԣ�\"}");
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
