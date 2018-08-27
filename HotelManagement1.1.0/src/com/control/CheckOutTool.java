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
import com.bo.CustomerCheckInfoBo;
import com.dao.CheckOutDao;
import com.dao.CustomerCheckInfoDao;
import com.dao.RoomDao;
import com.util.JsonUtil;
import com.vo.CustomerCheckInfo;

/**
 * Servlet implementation class CheckOutTool
 */
@WebServlet("/CheckOutTool")
public class CheckOutTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private CheckOutDao co_dao=new CheckOutDao();
    private CheckOutBo co_bo=new CheckOutBo();
    private CustomerCheckInfoDao cci_dao=new CustomerCheckInfoDao();
    private CustomerCheckInfoBo cci_bo=new  CustomerCheckInfoBo();
    private RoomDao room_dao=new RoomDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CheckTool ct=new CheckTool();
		//当结账界面跳转时，实现数据显示功能
		if(action.equals("getData")) {
			String roomNum=request.getParameter("roomNum");
			if(cci_bo.findByNum(roomNum)) {
				 CustomerCheckInfo cci=cci_dao.findByNum(roomNum);
				 int price=ct.getOutPrice(sdf.format(new Date()), cci.getCheckTime(),room_dao.findByNum(roomNum).getTypePrice() );//计算出需交纳的金额
				 //System.out.println(price);
				 cci.setRecivable(cci.getRecivable()+price);//修改当前集合的需缴纳的金额值
				 out.println(JsonUtil.toJson(cci));
			}
		}else if(action.equals("updOut")) {
			String roomNum=request.getParameter("roomNum");
			int empId=1;
			//String outTime=request.getParameter("outTime");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			float discount=Float.parseFloat(request.getParameter("discount"));
			float recivable=Float.parseFloat(request.getParameter("recivable"));
			float actual=Float.parseFloat(request.getParameter("actual"));
			String remark=request.getParameter("remark");
			if(co_bo.updCheckOut(empId, sdf1.format(new Date()), discount, recivable, actual, remark, roomNum)) {
				room_dao.updateRoomState(roomNum, 3);
				out.write("{\"msg\":\"结账完成！\"}");
			}else {
				out.write("{\"msg\":\"结账失败，请重新尝试！\"}");
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
