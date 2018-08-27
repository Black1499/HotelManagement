package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.vo.VipConsumptionVo;

/**
 * vip消费表连接数据层
 * @author 杨万生
 *
 */
public class VipConsumptionDao {
	
	/**
	 * 查看所有的Vip充值记录
	 * @return
	 */
	public List<VipConsumptionVo> getVipall(){
		String sql = "select C.customerId,C.customerName ,C.customerIdNum,C.customerVIP,V.vipRecord,V.vipBlance ,";
		sql +=" V.inTime,V.vipIntegral,e.empName from vipConsumption V inner join customerInfo C on V.customerId = C.customerId ";
		sql +=" inner join employeeInfo E on E.empID = V.empId";
		List<VipConsumptionVo> list = new ArrayList();
		VipConsumptionVo vipCon = null;
		ResultSet rs = DBUtil.executeQuery(sql);
		try {
			while(rs.next()) {
				vipCon = new VipConsumptionVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerIdNum"),rs.getInt("customerVIP"),
						rs.getFloat("vipRecord"),rs.getFloat("vipBlance"),
						rs.getString("inTime"),rs.getFloat("vipIntegral"),rs.getString("empName"));
				list.add(vipCon);
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param page
	 * @param eng
	 * @return
	 */
	public  List<VipConsumptionVo> getPageVIP(int page , int eng ){
		int start = (page-1)*eng+1;
		int end = page*eng;
		String sql = "select * from( select C.customerId,C.customerName ,C.customerIdNum,C.customerVIP,V.vipRecord,V.vipBlance ,";
		sql +="V.inTime,V.vipIntegral,e.empName ,ROW_NUMBER() over(order by  C.customerId) rows from ";
		sql +="vipConsumption V inner join customerInfo C on V.customerId = C.customerId ";
		sql +="inner join employeeInfo E on E.empID = V.empId ) o  where   o.rows>=? and o.rows<=?";
		Object[] obj = {start,end};
		List<VipConsumptionVo> list = new ArrayList();
		VipConsumptionVo vipCon = null;
		ResultSet rs = DBUtil.excuteQuery(sql, obj);
		try {
			while(rs.next()) {
				vipCon = new VipConsumptionVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerIdNum"),rs.getInt("customerVIP"),
						rs.getFloat("vipRecord"),rs.getFloat("vipBlance"),
						rs.getString("inTime"),rs.getFloat("vipIntegral"),rs.getString("empName"));
				list.add(vipCon);
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * vip客户充值
	 * @param name 客户名字
	 * @param customrId 客户编号
	 * @param vipRecord 充值金额
	 * @param empId 操作员编号
	 * @return
	 */
	public int setVipAdd(String name,int customrId,float vipRecord, int empId) {
		String sql = "exec sp_vipRecord ?,?,?,? ";
		Object[] obj = {name,customrId,vipRecord,empId};
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	/**
	 * 搜索查看客户的充值记录
	 * @param name 客户名字
	 * @return 
	 */
	public List<VipConsumptionVo> getVipById(String name) {
		List<VipConsumptionVo> list = new ArrayList();
		VipConsumptionVo vipVo = null ;
		String sql ="select C.customerId,C.customerName ,C.customerIdNum,C.customerVIP ,";
		sql+="V.vipRecord,V.inTime,V.vipBlance ,V.vipIntegral,e.empName from ";
		sql+=" vipConsumption V inner join customerInfo C on V.customerId = C.customerId ";
		sql+=" inner join employeeInfo E on E.empID = V.empId ";
		sql+=" where C.customerName like ? ";
		String customName = "%"+name+"%";
		Object[] objb = {customName};
		ResultSet rs = DBUtil.excuteQuery(sql, objb);
		try {
			while(rs.next()) {
				vipVo = new VipConsumptionVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("customerIdNum"),rs.getInt("customerVIP"),
						rs.getFloat("vipRecord"),rs.getFloat("vipBlance"),
						rs.getString("inTime"),rs.getFloat("vipIntegral"),rs.getString("empName"));
				list.add(vipVo);
			}
			DBUtil.closeConn();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 积分消费
	 * @param customerId VIP客户编号
	 * @param vipConsumption 要消费的积分数量
	 * @return
	 */
	public int setVipIntegral(int customerId,int vipConsumption ) {
		String sql = "update vipConsumption set vipIntegral=(vipIntegral-?) where customerId = ? and vipId = ";
		sql +=" ( select top 1 vipId from vipConsumption where customerId = ? order by vipBlance desc)";
		Object[] obj = {vipConsumption,customerId,customerId};
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	/**
	 * 用户自主充值
	 * @param customrId 用户编号
	 * @param vipRecord 充值金额
	 * @return
	 */
	public int setVipAdd(int customrId,float vipRecord) {
		String sql = "insert into vipConsumption(customerId,empId,vipRecord,vipBlance,vipIntegral,inTime,vipRemark) values ( ";
		sql +="?,1,?,((select top 1 vipBlance from vipConsumption where customerId =? order by vipBlance desc) + ?),";
		sql +="((select top 1 vipIntegral from vipConsumption where customerId =? order by vipIntegral desc )+?),GETDATE(),";
		sql +="'用户自主充值')";
		Object[] obj = {customrId,vipRecord,customrId,vipRecord,customrId,vipRecord};
		return DBUtil.excuteUpdate(sql, obj);
	}
	/**
	 * 用户注册，初始化数据
	 * @param customrId 用户编号
	 * @return
	 */
	public int setVipAdd(int customrId) {
		String sql = "insert into vipConsumption(customerId,empId,vipRecord,vipBlance,";
		sql +="vipIntegral,inTime,vipRemark) values (?,1,0,0,0,GETDATE(),'用户注册')";
		Object[] obj = {customrId};
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	
	/**
	 * 搜索查看客户的充值记录
	 * @param customerId 客户编号
	 * @return 
	 */
	public List<VipConsumptionVo> getVipById(int customerId) {
		List<VipConsumptionVo> list = new ArrayList();
		VipConsumptionVo vipVo = null ;
		String sql ="select C.customerId,C.customerName ,V.vipRemark,C.customerVIP ,";
		sql+="V.vipRecord,V.inTime,V.vipBlance ,V.vipIntegral,e.empName from ";
		sql+=" vipConsumption V inner join customerInfo C on V.customerId = C.customerId ";
		sql+=" inner join employeeInfo E on E.empID = V.empId ";
		sql+=" where C.customerId =? order by V.inTime desc ";
		Object[] objb = {customerId};
		ResultSet rs = DBUtil.excuteQuery(sql, objb);
		try {
			while(rs.next()) {
				vipVo = new VipConsumptionVo(
						rs.getInt("customerId"),rs.getString("customerName"),
						rs.getString("vipRemark"),rs.getInt("customerVIP"),
						rs.getFloat("vipRecord"),rs.getFloat("vipBlance"),
						rs.getString("inTime"),rs.getFloat("vipIntegral"),rs.getString("empName"));
				list.add(vipVo);
			}
			DBUtil.closeConn();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
}
