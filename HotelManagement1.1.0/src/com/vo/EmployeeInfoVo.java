package com.vo;
/**
 * Ա����ʵ����
 * @author ������
 *
 */
public class EmployeeInfoVo {
	private int empId;
	private String empAccountNum;
	private String empPassword;
	private String empName;
	private String empSex;
	private String empIdNum;
	private String empPhone;
	private String empAddress;
	private int empAdmin;
	private String empRemark;
	private String empImg;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpAccountNum() {
		return empAccountNum;
	}
	public void setEmpAccountNum(String empAccountNum) {
		this.empAccountNum = empAccountNum;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpIdNum() {
		return empIdNum;
	}
	public void setEmpIdNum(String empIdNum) {
		this.empIdNum = empIdNum;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public int getEmpAdmin() {
		return empAdmin;
	}
	public void setEmpAdmin(int empAdmin) {
		this.empAdmin = empAdmin;
	}
	public String getEmpRemark() {
		return empRemark;
	}
	public void setEmpRemark(String empRemark) {
		this.empRemark = empRemark;
	}
	public String getEmpImg() {
		return empImg;
	}
	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}
	@Override
	public String toString() {
		return "EmployeeInfoVo [empId=" + empId + ", empAccountNum=" + empAccountNum + ", empPassword=" + empPassword
				+ ", empName=" + empName + ", empSex=" + empSex + ", empIdNum=" + empIdNum + ", empPhone=" + empPhone
				+ ", empAddress=" + empAddress + ", empAdmin=" + empAdmin + ", empRemark=" + empRemark + ", empImg="
				+ empImg + "]";
	}
	public EmployeeInfoVo(int empId, String empAccountNum, String empPassword, String empName, String empSex,
			String empIdNum, String empPhone, String empAddress, int empAdmin, String empRemark, String empImg) {
		super();
		this.empId = empId;
		this.empAccountNum = empAccountNum;
		this.empPassword = empPassword;
		this.empName = empName;
		this.empSex = empSex;
		this.empIdNum = empIdNum;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
		this.empAdmin = empAdmin;
		this.empRemark = empRemark;
		this.empImg = empImg;
	}

	public EmployeeInfoVo(String empAccountNum, String empPassword, String empName, String empSex,
			String empIdNum, String empPhone, String empAddress, String empRemark, String empImg) {
		super();
		this.empAccountNum = empAccountNum;
		this.empPassword = empPassword;
		this.empName = empName;
		this.empSex = empSex;
		this.empIdNum = empIdNum;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
		this.empRemark = empRemark;
		this.empImg = empImg;
	}
	
	/**
	 * 
	 * @param empAccountNum Ա���˺�
	 * @param empName Ա������
	 * @param empSex Ա���Ա�
	 * @param empIdNum Ա��֤����
	 * @param empPhone Ա���绰
	 * @param empAddress Ա��סַ
	 * @param empRemark Ա����ע
	 * @param empImg Ա��ͼƬ
	 */
	
	public EmployeeInfoVo() {}
	public EmployeeInfoVo(String empAccountNum, String empPassword, String empName, String empSex, String empIdNum,
			String empPhone, String empAddress, int empAdmin, String empRemark, String empImg) {
		super();
		this.empAccountNum = empAccountNum;
		this.empPassword = empPassword;
		this.empName = empName;
		this.empSex = empSex;
		this.empIdNum = empIdNum;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
		this.empAdmin = empAdmin;
		this.empRemark = empRemark;
		this.empImg = empImg;
	}
	public EmployeeInfoVo(String empAccountNum,String empName, String empSex, String empIdNum,
			String empPhone, String empAddress, String empRemark, String empImg) {
		super();
		this.empAccountNum = empAccountNum;
		this.empName = empName;
		this.empSex = empSex;
		this.empIdNum = empIdNum;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
		this.empRemark = empRemark;
		this.empImg = empImg;
	}
}
