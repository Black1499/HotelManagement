package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.JDBCUtils;
import com.vo.CustomerInfo;
import com.vo.CustomerInfoVo;
import com.vo.RoomType;
import com.vo.VipConsumptionVo;

/**
 * �ͻ���Ϣ�����ݿ����Ӳ�
 * @author ������
 *
 */
public class CustomerInfoDao {
	/**
	 * ��ѯ�û���������Ϣ
	 * @return �����û�ʵ�����List����
	 */
	public List<CustomerInfoVo> getCtiAll(){
		List<CustomerInfoVo> list = new ArrayList();
		String sql = "select * from customerInfo";
		ResultSet rs = DBUtil.executeQuery(sql);
		CustomerInfoVo ctiVo = null ;
		try {
			while(rs.next()) {
				ctiVo = new CustomerInfoVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerPhone"),rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),rs.getInt("customerCount"),rs.getString("customerRemark"));
				list.add(ctiVo);
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<CustomerInfoVo> getPager(int page,int limit){
		int start=(page-1)*limit+1;
		int end=page*limit;
        List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
        String sql = "select * from(SELECT *,ROW_NUMBER() over(order by customerId) row FROM [customerInfo]) t where t.row>=? and t.row<=?";
        //ִ�в�ѯ����List<Product>
        Object[] o={start,end};
        ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfoVo cti = null ;
		try {
			while(rs.next()) {
				cti=new CustomerInfoVo();
				cti.setCustomerId(rs.getInt(1));
				cti.setCustomerName(rs.getString(2));
				cti.setCustomerPhone(rs.getString(3));
				cti.setCustomerIdNum(rs.getString(4));
				cti.setCustomerVIP(rs.getInt(5));
				cti.setCustomerCount(rs.getInt(6));
				cti.setCustomerRemark(rs.getString(7));
				list.add(cti);
			}
			//DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
        
	}
	public int getCount() {
		String sql="select count(*) from customerInfo";
		ResultSet rs=DBUtil.executeQuery(sql);
		int num=0;
		try {
			while(rs.next()) {
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * ��ѯ���û�
	 * @return
	 */
	public List<CustomerInfoVo> getCit(int page,int limit,int num){
		int start=(page-1)*limit+1;
		int end=page*limit;
        List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
        String sql = "select * from(SELECT *,ROW_NUMBER() over(order by customerId) row FROM [customerInfo] where customerVIP = ?) t where t.row>=? and t.row<=?";
        //ִ�в�ѯ����List<Product>
        Object[] o={num,start,end};
        ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfoVo ctiVo = null ;
		try {
			while(rs.next()) {
				ctiVo = new CustomerInfoVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerPhone"),rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),rs.getInt("customerCount"),rs.getString("customerRemark"));
				list.add(ctiVo);
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * �����ֲ�ѯ�ͻ�
	 * @param customerName �ͻ�����
	 * @return �����û�ʵ���༯��
	 */
	public List<CustomerInfoVo> getCitByName(int page,int limit,String customerName){
		int start=(page-1)*limit+1;
		int end=page*limit;
        List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
        String sql = "select * from(SELECT *,ROW_NUMBER() over(order by customerId) row FROM [customerInfo] where customerName like  '%"+customerName+"%' ) t where t.row>=? and t.row<=?";
        //ִ�в�ѯ����List<Product>
        Object[] o={start,end};
        ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfoVo ctiVo = null ;
		try {
			while(rs.next()) {
				ctiVo = new CustomerInfoVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerPhone"),rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),rs.getInt("customerCount"),rs.getString("customerRemark"));
				list.add(ctiVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public CustomerInfo findById(int customerId) {
		String sql = "select * from customerInfo where customerId=?";
		Object[] o= {customerId};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfo cus = null;
		try{
			while(rs.next()){
				cus = new CustomerInfo(
						rs.getInt("customerId"),
						rs.getString("customerName"),
						rs.getString("customerPhone"),
						rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),
						rs.getInt("customerCount"),
						rs.getString("customerRemark")
						);
			}
		}catch(Exception x){
			System.out.println(x);
		}
		return cus;
	}
	
	public CustomerInfo findByName(String customerName) {
		String sql = "select * from customerInfo where customerName=?";
		Object[] o= {customerName};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfo cus = null;
		try{
			while(rs.next()){
				cus = new CustomerInfo(
						rs.getInt("customerId"),
						rs.getString("customerName"),
						rs.getString("customerPhone"),
						rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),
						rs.getInt("customerCount"),
						rs.getString("customerRemark")
						);
			}
			
		}catch(Exception x){
			System.out.println(x);
		}
		return cus;
	}
	
	
	
	
	
	
	
	
	/**
	 * ��ѯ�ͷ��Ƿ���ڣ�����vip��ֵ
	 * @param cutName �ͻ�����
	 * @param cutId �ͻ�Id���
	 * @return ����0�ÿͻ�������
	 */
	public int getByIdCut(String cutName,int cutId) {
		int  cutIdVo = 0;
		String sql = "select * from customerInfo where customerId=? and customerName=?";
		Object[] obj = {cutId , cutName};
		ResultSet rs = DBUtil.excuteQuery(sql, obj);
		try {
			while(rs.next()) {
				cutIdVo = rs.getInt("customerId");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cutIdVo;
	}
	
	/**
	 * ����vip
	 * @param customerName �ͻ�����
	 * @param customerIdNum �ͻ�֤����
	 * @param customerPhone �ͻ��绰����
	 * @return ����1ִ�гɹ�
	 */
	public int setCitVip(String customerName,String customerIdNum,String customerPhone) {
		String sql = "exec sp_Orders ?,?,?";
		Object[] obj = {customerName,customerIdNum,customerPhone};
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	/**
	 * ����ָ����ŵ�VIP
	 * */
	public CustomerInfo judgeVIP(int customerId){
		String sql = "select * from customerInfo where customerVIP = 1 and customerId=?";
		Object[] o= {customerId};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfo cus = null;
		try{
			while(rs.next()){
				cus = new CustomerInfo(
						rs.getInt("customerId"),
						rs.getString("customerName"),
						rs.getString("customerPhone"),
						rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),
						rs.getInt("customerCount"),
						rs.getString("customerRemark")
						);
			
			}
			DBUtil.closeConn();//�ر���������
		}catch(Exception x){
			System.out.println(x);
		}
		return cus;
	}
	
	
	public CustomerInfo findByNamePhone(String name,String phone){
		String sql = "select * from customerInfo where customerVIP = 1 and customerName=? and customerPhone=?";
		Object[] o= {name,phone};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfo cus = null;
		try{
			while(rs.next()){
				cus = new CustomerInfo(
						rs.getInt("customerId"),
						rs.getString("customerName"),
						rs.getString("customerPhone"),
						rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),
						rs.getInt("customerCount"),
						rs.getString("customerRemark")
						);
			
			}
			DBUtil.closeConn();//�ر���������
		}catch(Exception x){
			System.out.println(x);
		}
		return cus;
	}
	
	/**
	 * �û�ע��
	 * @param name �û�����
	 * @param password �û�����
	 * @param idNum �û�֤����
	 * @param phone �û��绰
	 * @return ����1ע��ɹ�
	 */
	public int cusRegister(String name , String password,String idNum ,String phone ) {
		String sql = "insert into customerInfo(customerName,customerPhone,customerIdNum,";
		sql +="customerRemark,customerVIP,customerCount) values(?,?,?,?,1,0)";
		Object[] obj = {name,phone,idNum,password};
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	
	/**
	 * ��ѯ�ͻ���VIP��Ϣ
	 * @param cusId
	 * @return
	 */
	public VipConsumptionVo getVipInfo(int cusId) {
		String sql = " select top 1  C.customerId,C.customerName ,C.customerIdNum,C.customerVIP,V.vipRecord,V.vipBlance ,";
		sql +="V.inTime,V.vipIntegral,e.empName ,ROW_NUMBER() over(order by  C.customerId) rows from ";
		sql +=" vipConsumption V inner join customerInfo C on V.customerId = C.customerId ";
		sql +="inner join employeeInfo E on E.empID = V.empId where C.customerId =? order by V.vipId desc";
		Object[] obj = {cusId};
		ResultSet rs =  DBUtil.excuteQuery(sql, obj);
		VipConsumptionVo vipVo = null;
		try {
			while(rs.next()) {
				vipVo = new VipConsumptionVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerIdNum"),rs.getInt("customerVIP"),
						rs.getFloat("vipRecord"),rs.getFloat("vipBlance"),
						rs.getString("inTime"),rs.getFloat("vipIntegral"),rs.getString("empName"));
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vipVo;
	}
	
	/**
	 * �û���½
	 * @param cusId �û���ţ����ֻ�����
	 * @param cusPwd  �û�����
	 */
	public CustomerInfo cusLongin(String cusId,String cusPwd) {
		String sql = "select * from customerInfo where customerPhone =? and customerRemark=?";
		Object[] o= {cusId,cusPwd};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		CustomerInfo cus = null;
		try{
			while(rs.next()){
				cus = new CustomerInfo(
						rs.getInt("customerId"),
						rs.getString("customerName"),
						rs.getString("customerPhone"),
						rs.getString("customerIdNum"),
						rs.getInt("customerVIP"),
						rs.getInt("customerCount"),
						rs.getString("customerRemark")
						);
			
			}
			DBUtil.closeConn();//�ر���������
		}catch(Exception x){
			System.out.println(x);
		}
		return cus;
	}
	
	
	
	
	public int addCust(String name , String idNum ,String phone ) {
		String sql = "insert into customerInfo values(?,?,?,0,1,'')";
		Object[] obj = {name,phone,idNum};
		return DBUtil.excuteUpdate(sql, obj);
	}
}
