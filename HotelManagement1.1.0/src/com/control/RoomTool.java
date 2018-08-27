package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.RoomBo;
import com.bo.RoomTypeBo;
import com.dao.RoomDao;
import com.dao.RoomTypeDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.util.JsonUtil;
import com.vo.DataClass;
import com.vo.Room;
import com.vo.RoomType;

/**
 * Servlet implementation class RoomTool
 */
@WebServlet("/RoomTool")
public class RoomTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RoomDao room_dao=new RoomDao();
    private RoomBo room_bo=new RoomBo();
    private RoomTypeDAO rtd=new RoomTypeDAO();
    private RoomTypeBo rtb=new RoomTypeBo();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		ArrayList<Room> list=new ArrayList<Room>();
		if(action.equals("getPage")) {
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			if(room_bo.getPage(page, limit)) {
				int count=room_dao.selectAll().size();//���еķ�����
				out.println(DataClass.ok(count,room_dao.getPage(1, count)).toJson());
			}
		}else if(action.equals("getType")) {
			if(rtb.getAll()) {
				ArrayList<RoomType> listType=rtd.getAll();
				out.println(JsonUtil.toJson(listType));
			}
		}else if(action.equals("addRoom")) {
			String roomNum=request.getParameter("roomNum");
			int typeId=Integer.parseInt(request.getParameter("roomType"));
			String roomPhone=request.getParameter("roomPhone");
			int roomAvailable=Integer.parseInt(request.getParameter("roomAvailable"));
			String roomRemark=request.getParameter("roomRemark");
			if(room_bo.insertRoom(roomNum, typeId, roomPhone, roomAvailable, 1, roomRemark)) {
				out.write("{\"msg\":\"��ӳɹ�\"}");
			}else {
				out.write("{\"msg\":\"���ʧ�ܣ������³��ԣ�\"}");
			}
		}else if(action.equals("judgeRoom")) {
			String roomNum=request.getParameter("roomNum");
			if(room_bo.findByNum(roomNum)) {
				out.write("{\"msg\":\"�÷����Ѵ��ڣ����������룡\"}");
			}
		}else if(action.equals("delRoom")) {
			String roomNum=request.getParameter("roomNum");
			if(room_bo.deleteRoom(roomNum)) {
				out.write("{\"msg\":\"ɾ���ɹ�\"}");
			}
		}else if(action.equals("updRoom")) {
			String roomNum=request.getParameter("roomNum");
			int typeId=Integer.parseInt(request.getParameter("roomType"));
			String roomPhone=request.getParameter("roomPhone");
			int roomAvailable=Integer.parseInt(request.getParameter("roomAvailable"));
			String roomRemark=request.getParameter("roomRemark");
			if(room_bo.updRoom(roomNum, typeId, roomPhone, roomAvailable, roomRemark)) {
				out.write("{\"msg\":\"�޸ĳɹ�\"}");
			}else {
				out.write("{\"msg\":\"�޸�ʧ�ܣ������³��ԣ�\"}");
			}
		}else if(action.equals("stateChage")) {
			String roomNum=request.getParameter("roomNum");
			if(room_bo.updateRoomState(roomNum, 0)) {
				out.write("{\"msg\":\"�޸ĳɹ�\"}");
				
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
