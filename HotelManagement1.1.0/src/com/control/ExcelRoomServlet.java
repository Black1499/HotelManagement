package com.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dao.RoomDao;
import com.dao.RoomTypeDAO;
import com.util.DBUtil;
import com.vo.DataClass;
import com.vo.Room;

/**
 * Servlet implementation class OutRoomServlet
 */
@WebServlet("/ExcelRoomServlet")
public class ExcelRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equals("outRoom")) {
			outRoomInfo(response); 
		}else if(action.equals("inRoom")) {
			String path=getPath(request, response);
			try {
				readExcel(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataClass r = new DataClass();
	        r.setCode(0);
	        r.setMsg("上传成功");
	        response.getWriter().print(r.toJson());
		}
		
       
	}
	/*
	 * 文件上传
	 * */
	private String getPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String savePath = this.getServletConfig().getServletContext().getRealPath("");
		savePath = savePath + "img\\";
		System.out.println(savePath);
		File f1 = new File(savePath);
		if (!f1.exists()) {
		    f1.mkdirs();
		}

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = null;
		try {
		    fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
		    System.out.println(ex.getMessage());
		}

		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		while (it.hasNext()) {
		    FileItem item = it.next();
		    if (!item.isFormField()) {
		        name = item.getName();
		        long size = item.getSize();
		        String type = item.getContentType();
		        System.out.println(size + " " + type);
		        if (name == null || name.trim().equals("")) {
		            continue;
		        }

		        // 扩展名格式：
		        if (name.lastIndexOf(".") >= 0) {
		            extName = name.substring(name.lastIndexOf("."));
		        }

		        File file = null;
		        do {
		            // 生成文件名：
		            name = UUID.randomUUID().toString();
		            file = new File(savePath + name + extName);
		        } while (file.exists());
		        
		        File saveFile = new File(savePath + name + extName);
		        try {
		            item.write(saveFile);
		        } catch (Exception exp) {
		            response.getWriter().write(exp.getMessage());
		            exp.printStackTrace();
		        }
		    }
		}
		return savePath+name+extName;
	}
	
	 public static void readExcel(String fileName) throws Exception{
	        InputStream is = new FileInputStream(new File(fileName));
	        Workbook hssfWorkbook = null;
	        if (fileName.endsWith("xlsx")){
	           hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
	        }else if (fileName.endsWith("xls")){
	            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
	        }
	       // HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	       // XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
	        Room room = null;
	        ArrayList<Room> list = new ArrayList<Room>();
	        // 循环工作表Sheet
	        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // 循环行Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                Row hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow != null) {
	                	room = new Room();
	                	Cell roomNum=hssfRow.getCell(0);
	                	Cell typeName=hssfRow.getCell(1);
	                	Cell typePrice=hssfRow.getCell(2);
	                	Cell typeDeposit=hssfRow.getCell(3);
	                	Cell roomPhone=hssfRow.getCell(4);
	                	Cell roomAvailable=hssfRow.getCell(5);
	                	Cell roomState=hssfRow.getCell(6);
	                	Cell roomRemark=hssfRow.getCell(7);
	                	if(roomNum!=null) {//如果为空证明该行无数据
	                		room.setRoomNum(roomNum.toString().substring(0,roomNum.toString().indexOf('.')));
		                	room.setTypeName(typeName.toString());
		                	room.setTypePrice(Integer.parseInt(typePrice.toString().substring(0,typePrice.toString().indexOf('.'))));
		                	room.setTypeDeposit(Integer.parseInt(typeDeposit.toString().substring(0,typeDeposit.toString().indexOf('.'))));
		                	room.setRoomPhone(roomPhone.toString().substring(0,roomPhone.toString().indexOf('.')));
		                	room.setRoomAvailable(Integer.parseInt(roomAvailable.toString().substring(0,roomAvailable.toString().indexOf('.'))));
		                	room.setRoomState(Integer.parseInt(roomState.toString().substring(0,roomState.toString().indexOf('.'))));
		                	room.setRoomRemark(roomRemark.toString());
		                	list.add(room);
	                	}
	                	
	                }
	            }
	        }
	       System.out.println(list.size());
	       RoomDao rd=new RoomDao();
	       RoomTypeDAO rtd=new RoomTypeDAO();
	       for(int i=0;i<list.size();i++) {
	    	  String roomNum=list.get(i).getRoomNum();
	    	  int typeId=rtd.getAll(list.get(i).getTypeName()).getTypeId();
	    	  String roomPhone=list.get(i).getRoomPhone();
	    	  int roomAvailable=list.get(i).getRoomAvailable();
	    	  int roomState=list.get(i).getRoomState();
	    	  String roomRemark=list.get(i).getTypeName();
	    	 // System.out.println(roomNum+" "+typeId+" "+roomPhone+" "+roomAvailable+" "+roomState+" "+roomRemark);
	    	  rd.insertRoom(roomNum, typeId, roomPhone, roomAvailable, roomState, roomRemark);
	       }
	    }
	/*
	 *输出房间信息
	 * */
	private void outRoomInfo(HttpServletResponse response) throws IOException {
		//创建HSSFWorkbook对象(excel的文档对象)
		 HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("房间信息表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("房间信息一览表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		//在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("房间编号");
        row2.createCell(1).setCellValue("类型名称");
        row2.createCell(2).setCellValue("房间价格");
        row2.createCell(3).setCellValue("房价押金");
        row2.createCell(4).setCellValue("房价电话");
        row2.createCell(5).setCellValue("房价可用");
        row2.createCell(6).setCellValue("房价状态");
        row2.createCell(7).setCellValue("房价备注");

        RoomDao rd=new RoomDao();
        ArrayList<Room> list=rd.selectAll();
        
        HSSFRow row=null;
        for(int i=0;i<list.size();i++) {
        	row=sheet.createRow(i+2);
        	row.createCell(0).setCellValue(list.get(i).getRoomNum());
        	row.createCell(1).setCellValue(list.get(i).getTypeName());
        	row.createCell(2).setCellValue(list.get(i).getTypePrice());
        	row.createCell(3).setCellValue(list.get(i).getTypeDeposit());
        	row.createCell(4).setCellValue(list.get(i).getRoomPhone());
        	row.createCell(5).setCellValue(list.get(i).getRoomAvailable());
        	row.createCell(6).setCellValue(list.get(i).getRoomState());
        	row.createCell(7).setCellValue(list.get(i).getRoomRemark());
        	
        }
		//输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=roomList.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
