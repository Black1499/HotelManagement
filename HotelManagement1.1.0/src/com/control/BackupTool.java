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
					out.write("{\"msg\":\"���ݿⱸ�ݳɹ���\"}");
				}else {
					out.write("{\"msg\":\"����ʧ�ܣ������³���\"}");
				}
			}
		}else if(action.equals("del")) {
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			if(dnn.delete(id)) {
				del(name);
				out.write("{\"msg\":\"ɾ���ɹ���\"}");
			}else {
				out.write("{\"msg\":\"ɾ��ʧ�ܣ������³��ԣ�\"}");
			}
		}else if(action.equals("restore")) {
			String name=request.getParameter("name");
			String path="D:\\DataBaseBackUp\\"+name;
			if(dbd.restoreData(path)) {
				out.write("{\"msg\":\"��ԭ�ɹ���\"}");
			}else {
				out.write("{\"msg\":\"��ԭʧ�ܣ������³��ԣ�\"}");
			}
		}
	}

	/**
	 * ����ļ��Ĵ�С
	 * @param size �ļ��ĳ���
	 * @return
	 */
	 public String setSize(int size) {
	        //��ȡ����sizeΪ��1705230
	        int GB = 1024 * 1024 * 1024;//����GB�ļ��㳣��
	        int MB = 1024 * 1024;//����MB�ļ��㳣��
	        int KB = 1024;//����KB�ļ��㳣��
	        DecimalFormat df = new DecimalFormat("0.00");//��ʽ��С��
	        String resultSize = "";
	        if (size / GB >= 1) {
	            //�����ǰByte��ֵ���ڵ���1GB
	            resultSize = df.format(size / (float) GB) + "GB   ";
	        } else if (size / MB >= 1) {
	            //�����ǰByte��ֵ���ڵ���1MB
	            resultSize = df.format(size / (float) MB) + "MB   ";
	        } else if (size / KB >= 1) {
	            //�����ǰByte��ֵ���ڵ���1KB
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
