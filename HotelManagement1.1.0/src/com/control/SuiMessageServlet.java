package com.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.SuiMessageBo;
import com.dao.SuiMessageDao;
import com.util.JsonUtil;

/**
 * Servlet implementation class SuiMessageServlet
 */
@WebServlet("/SuiMessageServlet")
public class SuiMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuiMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		PrintWriter out=response.getWriter();
		SuiMessageDao smd=new SuiMessageDao();
		SuiMessageBo smb=new SuiMessageBo();
		if(action.equals("getReserve")) {
			if(smb.findReserve(name, phone)) {
				out.write(JsonUtil.toJson(smd.findReserve(name, phone)));
				//System.out.println(JsonUtil.toJson(smd.findReserve(name, phone)));
			}
		}else if(action.equals("getCheck")) {
			if(smb.findCheck(name, phone)) {
				out.write(JsonUtil.toJson(smd.findCheck(name, phone)));
			}
		}else if(action.equals("getOut")) {
			if(smb.findOut(name, phone)) {
				out.write(JsonUtil.toJson(smd.findOut(name, phone)));
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
