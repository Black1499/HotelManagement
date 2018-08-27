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
import com.dao.RoomDao;
import com.util.JsonUtil;
import com.vo.Room;

/**
 * Servlet implementation class JudgeRoom
 */
@WebServlet("/JudgeRoom")
public class JudgeRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JudgeRoom() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RoomBo room_bo=new RoomBo();
    private RoomDao room_dao=new RoomDao();
    private ArrayList<Room> list=new ArrayList<Room>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String action=request.getParameter("action");
	    PrintWriter out=response.getWriter();
		if(action.equals("getAllRoom")) {
			delay();
			if(room_bo.selectAll()) {
				list=room_dao.selectAll();
				out.println(JsonUtil.toJson(list));
			}
		}else if(action.equals("getPartRoom")) {
			int state=Integer.parseInt(request.getParameter("state"));
			if(state==4) {
				delay();
				if(room_bo.findByAvailable(1)) {
					list = room_dao.findByAvailable(1);
					out.println(JsonUtil.toJson(list));
				}
			}else if(state>=0 && state<=3) {
				list=findByState(state);
				out.println(JsonUtil.toJson(list));
			}
		}else if(action.equals("getRoom")) {
			String roomNum=request.getParameter("roomNum");
			if(room_bo.findByNum(roomNum)) {
				Room room=room_dao.findByNum(roomNum);
				out.println(JsonUtil.toJson(room));
			}else {
				out.write("{\"msg\":\"该房间号不存在,请重新输入！\"}");
			}
		}
		
	}
	//根据房间状态查找房间
	public ArrayList<Room> findByState(int state){
		delay();
		if(room_bo.findByState(state)) {
			return room_dao.findByState(state);
		}
		return null;
	}
	
	public void delay(){
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		    e.printStackTrace();
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
