package com.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.DataBackupBo;
import com.dao.DataBackupDao;
import com.vo.DataClass;

/**
 * Servlet implementation class BackupTool
 */
@WebServlet("/BackupTool")
public class BackupTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackupTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		DataBackupDao dbd=new DataBackupDao();
		DataBackupBo dnn=new DataBackupBo();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(action.equals("getData")) {
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			//System.out.println(page+limit);
			if(dnn.getAll()) {
				int count=dbd.getAll().size();
				out.write(DataClass.ok(count,dbd.getAll()).toJson());
			}
		}else if(action.equals("addDataBase")) {
			String path="D:\\DataBaseBackUp\\";
			File f=new File(path);
			if(f.exists()) {
				f.mkdir();
			}
			String dataPath=dbd.backUp(path);
			if(dataPath!="") {
				File file=new File(dataPath);
				String size=setSize((int)file.length());
				String name=file.getName();
				if(dnn.add(name, size,sdf.format(new Date()) )) {
					out.write("{\"msg\":\"数据库备份成功！\"}");
				}else {
					out.write("{\"msg\":\"备份失败，请重新尝试\"}");
				}
			}
		}else if(action.equals("del")) {
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			if(dnn.delete(id)) {
				del(name);
				out.write("{\"msg\":\"删除成功！\"}");
			}else {
				out.write("{\"msg\":\"删除失败，请重新尝试！\"}");
			}
		}else if(action.equals("restore")) {
			String name=request.getParameter("name");
			String path="D:\\DataBaseBackUp\\"+name;
			if(dbd.restoreData(path)) {
				out.write("{\"msg\":\"还原成功！\"}");
			}else {
				out.write("{\"msg\":\"还原失败，请重新尝试！\"}");
			}
		}
	}

	/**
	 * 获得文件的大小
	 * @param size 文件的长度
	 * @return
	 */
	 public String setSize(int size) {
	        //获取到的size为：1705230
	        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
	        int MB = 1024 * 1024;//定义MB的计算常量
	        int KB = 1024;//定义KB的计算常量
	        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
	        String resultSize = "";
	        if (size / GB >= 1) {
	            //如果当前Byte的值大于等于1GB
	            resultSize = df.format(size / (float) GB) + "GB   ";
	        } else if (size / MB >= 1) {
	            //如果当前Byte的值大于等于1MB
	            resultSize = df.format(size / (float) MB) + "MB   ";
	        } else if (size / KB >= 1) {
	            //如果当前Byte的值大于等于1KB
	            resultSize = df.format(size / (float) KB) + "KB   ";
	        } else {
	            resultSize = size + "B   ";
	        }
	       return resultSize;
	    }
	 
	 
	 
	 public void del(String name) {
		 String path="D:\\DataBaseBackUp\\";
		 File file=new File(path+name);
		 file.delete();
	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
