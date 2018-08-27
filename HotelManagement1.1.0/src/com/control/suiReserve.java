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
import com.util.JsonUtil;
import com.vo.Room;
import com.vo.RoomType;

/**
 * Servlet implementation class suiReserve
 */
@WebServlet("/suiReserve")
public class suiReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suiReserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private RoomTypeDAO rtd=new RoomTypeDAO();
    private RoomTypeBo  rtb=new RoomTypeBo();
    private RoomDao rd=new RoomDao();
    private RoomBo  rb=new RoomBo();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter  out=response.getWriter();
		if(action.equals("getType")) {
			if(rtb.getAll()) {
				ArrayList<RoomType> list=rtd.getAll();
				out.write(JsonUtil.toJson(list));
			}
		}else if(action.equals("getRoom")) {
			String typeName=request.getParameter("typeName");
			int typeId=rtd.getAll(typeName).getTypeId();
			if(rb.findByType(typeId)) {
				out.write(JsonUtil.toJson(rd.findByType(typeId)));
				//System.out.println(JsonUtil.toJson(rd.findByType(typeId)));
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
