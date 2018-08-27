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
import com.vo.RoomType;

/**
 * Servlet implementation class RoomTypeTool
 */
@WebServlet("/RoomTypeTool")
public class RoomTypeTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RoomTypeTool() {
        super();
    }

    private RoomTypeDAO roomtype_dao=new RoomTypeDAO();
    private RoomTypeBo roomtype_bo=new RoomTypeBo();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		if(action.equals("getAll")) {
			if(roomtype_bo.getAll()) {
				ArrayList<RoomType> listType=roomtype_dao.getAll();
				out.println(JsonUtil.toJson(listType));
				System.out.println(JsonUtil.toJson(listType));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
