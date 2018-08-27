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
		//����HSSFWorkbook����(excel���ĵ�����)
		 HSSFWorkbook wb = new HSSFWorkbook();
		//�����µ�sheet����excel�ı���
		HSSFSheet sheet=wb.createSheet("�ͻ���Ϣ��");
		//��sheet�ﴴ����һ�У�����Ϊ������(excel����)��������0��65535֮����κ�һ��
		HSSFRow row1=sheet.createRow(0);
		//������Ԫ��excel�ĵ�Ԫ�񣬲���Ϊ��������������0��255֮����κ�һ��
       HSSFCell cell=row1.createCell(0);
       //���õ�Ԫ������
       cell.setCellValue("�ͻ���Ϣһ����");
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
       sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		//��sheet�ﴴ���ڶ���
       HSSFRow row2=sheet.createRow(1);
       //������Ԫ�����õ�Ԫ������
       row2.createCell(0).setCellValue("�ͻ����");
       row2.createCell(1).setCellValue("�ͻ�����");
       row2.createCell(2).setCellValue("�绰����");
       row2.createCell(3).setCellValue("֤������");
       row2.createCell(4).setCellValue("�Ƿ�VIP");
       row2.createCell(5).setCellValue("��ס����");
       row2.createCell(6).setCellValue("�ͻ���ע");

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
       		row.createCell(4).setCellValue("��");
       	else
       		row.createCell(4).setCellValue("��");
       	row.createCell(5).setCellValue(list.get(i).getCustomerCount());
       	row.createCell(6).setCellValue(list.get(i).getCustomerRemark());
       	
       }
		//���Excel�ļ�
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
