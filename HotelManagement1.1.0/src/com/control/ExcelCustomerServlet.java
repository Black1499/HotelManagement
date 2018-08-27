package com.control;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.dao.CustomerInfoDao;
import com.dao.EmployeeInfoDao;
import com.vo.CustomerInfoVo;
import com.vo.EmployeeInfoVo;

/**
 * Servlet implementation class ExcelCustomerServlet
 */
@WebServlet("/ExcelCustomerServlet")
public class ExcelCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建HSSFWorkbook对象(excel的文档对象)
		 HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("客户信息表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
       HSSFCell cell=row1.createCell(0);
       //设置单元格内容
       cell.setCellValue("客户信息一览表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
       sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		//在sheet里创建第二行
       HSSFRow row2=sheet.createRow(1);
       //创建单元格并设置单元格内容
       row2.createCell(0).setCellValue("客户编号");
       row2.createCell(1).setCellValue("客户姓名");
       row2.createCell(2).setCellValue("电话号码");
       row2.createCell(3).setCellValue("证件号码");
       row2.createCell(4).setCellValue("是否VIP");
       row2.createCell(5).setCellValue("入住次数");
       row2.createCell(6).setCellValue("客户备注");

       CustomerInfoDao cif=new CustomerInfoDao();
       List<CustomerInfoVo> list=cif.getCtiAll();
       HSSFRow row=null;
       for(int i=0;i<list.size();i++) {
       	row=sheet.createRow(i+2);
       	row.createCell(0).setCellValue(list.get(i).getCustomerId());
       	row.createCell(1).setCellValue(list.get(i).getCustomerName());
       	row.createCell(2).setCellValue(list.get(i).getCustomerPhone());
       	row.createCell(3).setCellValue(list.get(i).getCustomerIdNum());
       	if(list.get(i).getCustomerVIP()==0)
       		row.createCell(4).setCellValue("否");
       	else
       		row.createCell(4).setCellValue("是");
       	row.createCell(5).setCellValue(list.get(i).getCustomerCount());
       	row.createCell(6).setCellValue(list.get(i).getCustomerRemark());
       	
       }
		//输出Excel文件
       OutputStream output=response.getOutputStream();
       response.reset();
       response.setHeader("Content-disposition", "attachment; filename=CustomerInfo.xls");
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
