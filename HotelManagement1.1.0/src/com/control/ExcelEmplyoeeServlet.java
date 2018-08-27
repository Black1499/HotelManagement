package com.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import com.dao.EmployeeInfoDao;
import com.dao.RoomDao;
import com.dao.RoomTypeDAO;
import com.util.MD5;
import com.vo.DataClass;
import com.vo.EmployeeInfoVo;
import com.vo.Room;

/**
 * Servlet implementation class ExcelEmplyoeeServlet
 */
@WebServlet("/ExcelEmplyoeeServlet")
public class ExcelEmplyoeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelEmplyoeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equals("outEmp")) {
			outEmpInfo(response); 
		}else if(action.equals("inEmp")) {
			String path=getPath(request, response);
			try {
				readExcel(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataClass r = new DataClass();
	        r.setCode(0);
	        r.setMsg("�ϴ��ɹ�");
	        response.getWriter().print(r.toJson());
		}
	}
	/*
	 * �ļ��ϴ�
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

		        // ��չ����ʽ��
		        if (name.lastIndexOf(".") >= 0) {
		            extName = name.substring(name.lastIndexOf("."));
		        }

		        File file = null;
		        do {
		            // �����ļ�����
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
	        EmployeeInfoVo emp = null;
	        EmployeeInfoDao epd=new EmployeeInfoDao();
	       
	        // ѭ��������Sheet
	        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // ѭ����Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                Row hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow != null) {
	                	Cell cell0=hssfRow.getCell(0);
	                	Cell cell1=hssfRow.getCell(1);
	                	Cell cell2=hssfRow.getCell(2);
	                	Cell cell3=hssfRow.getCell(3);
	                	Cell cell4=hssfRow.getCell(4);
	                	Cell cell5=hssfRow.getCell(5);
	                	Cell cell6=hssfRow.getCell(6);
	                	Cell cell7=hssfRow.getCell(7);
	                	// Emp001 ��ԭ  ��  412817199701054031  13192321231 �麣�н���336�� 1.0 
	                	if(cell0!=null) {//���Ϊ��֤������������
	                		//System.out.println(cell0+" "+cell1+" "+cell2+" "+cell3+" "+cell4+" "+cell5+" "+cell6+" "+cell7);
	                		String empAcount=cell0.toString();
	                		String empName=cell1.toString();
	                		String empSex=cell2.toString();
	                		String empID=cell3.toString();
	                		String empPhone=cell4.toString();
	                		String empAdd=cell5.toString();
	                		int empAdmin=Integer.parseInt(cell6.toString().substring(0,cell6.toString().indexOf('.')));
	                		String empRemark=cell7.toString();
	                		Object[] o= {empAcount,MD5.getMD5("123456"),empName,empSex,empID,empPhone,empAdd,empAdmin,empRemark,""};
	                		epd.addEmps(o);
	                	}
	                	
	                }
	            }
	        }
	    }
	/*
	 *���������Ϣ
	 * */
	private void outEmpInfo(HttpServletResponse response) throws IOException {
		//����HSSFWorkbook����(excel���ĵ�����)
		 HSSFWorkbook wb = new HSSFWorkbook();
		//�����µ�sheet����excel�ı���
		HSSFSheet sheet=wb.createSheet("Ա����Ϣ��");
		//��sheet�ﴴ����һ�У�����Ϊ������(excel����)��������0��65535֮����κ�һ��
		HSSFRow row1=sheet.createRow(0);
		//������Ԫ��excel�ĵ�Ԫ�񣬲���Ϊ��������������0��255֮����κ�һ��
        HSSFCell cell=row1.createCell(0);
        //���õ�Ԫ������
        cell.setCellValue("Ա����Ϣһ����");
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		//��sheet�ﴴ���ڶ���
        HSSFRow row2=sheet.createRow(1);
        //������Ԫ�����õ�Ԫ������
        row2.createCell(0).setCellValue("Ա�����");
        row2.createCell(1).setCellValue("Ա���˺�");
        row2.createCell(2).setCellValue("Ա������");
        row2.createCell(3).setCellValue("Ա���Ա�");
        row2.createCell(4).setCellValue("���֤��");
        row2.createCell(5).setCellValue("�ֻ�����");
        row2.createCell(6).setCellValue("Ա��סַ");
        row2.createCell(7).setCellValue("�Ƿ񳬹�");
        row2.createCell(8).setCellValue("Ա����ע");

        EmployeeInfoDao ed=new EmployeeInfoDao();
        List<EmployeeInfoVo> list=ed.getEmpAll();
        HSSFRow row=null;
        for(int i=0;i<list.size();i++) {
        	row=sheet.createRow(i+2);
        	row.createCell(0).setCellValue(list.get(i).getEmpId());
        	row.createCell(1).setCellValue(list.get(i).getEmpAccountNum());
        	row.createCell(2).setCellValue(list.get(i).getEmpName());
        	row.createCell(3).setCellValue(list.get(i).getEmpSex());
        	row.createCell(4).setCellValue(list.get(i).getEmpIdNum());
        	row.createCell(5).setCellValue(list.get(i).getEmpPhone());
        	row.createCell(6).setCellValue(list.get(i).getEmpAddress());
        	row.createCell(7).setCellValue(list.get(i).getEmpAdmin());
        	row.createCell(8).setCellValue(list.get(i).getEmpRemark());
        	
        }
		//���Excel�ļ�
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=empList.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
