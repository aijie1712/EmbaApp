package com.emba.embaapp.model;

import java.io.Serializable;

/**
 * 登录用户信息
 * @author mikes
 * @version 2015-11-26 下午3:05:11
 * JSON 解析类
 */
public class UserAccountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1671198130008018846L;
	private String id;// 用户ID
	private String name;// 名字
	private String workno;
	private String account;// 账户
	private String password;
	private String cardno;
	private int age;
	private String sex;
	private String phone;
	private String mobile;
	private String email;
	private String address;
	private String gradeId;
	private String eduName;
	private String fireName;
	private String firePhone;
	private String description;
	private String state;
	private String imei;
	private String imsi;
	private String headimg;//用户头像
	private String collegeName;
	private String specileName;
	private String className;
	private String yearName;
	private String studyLength;
	private long overtime;
	private String qqnum;
	private String familyUserName;
	private String familyPhone;
	private String familyMobile;
	private long createtime;
	private long modifytime;
	private String icon;
	private String isOrg;
	private String token; // 融云token,唯一标识
	private String validDistance; //validDistance
	private String deviation;

	// 非数据库字段
	private String roleName;// 角色名字
	private String departName;// 首部门名称
	private String department;//部门名称
	private String qrcode;
	private String departs;//全部部门名称
	private String jobName;// 职位名称
	private int isSameDevice;// 是否是同一个设进行登录:0:是，1:不是
	//非服务器字段,非数据库字段
	private boolean isChecked;
	private String sortLetters;  //显示数据拼音的首字母

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkno() {
		return workno;
	}

	public void setWorkno(String workno) {
		this.workno = workno;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getEduName() {
		return eduName;
	}

	public void setEduName(String eduName) {
		this.eduName = eduName;
	}

	public String getFireName() {
		return fireName;
	}

	public void setFireName(String fireName) {
		this.fireName = fireName;
	}

	public String getFirePhone() {
		return firePhone;
	}

	public void setFirePhone(String firePhone) {
		this.firePhone = firePhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getSpecileName() {
		return specileName;
	}

	public void setSpecileName(String specileName) {
		this.specileName = specileName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getStudyLength() {
		return studyLength;
	}

	public void setStudyLength(String studyLength) {
		this.studyLength = studyLength;
	}

	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

	public String getQqnum() {
		return qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getFamilyUserName() {
		return familyUserName;
	}

	public void setFamilyUserName(String familyUserName) {
		this.familyUserName = familyUserName;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getFamilyMobile() {
		return familyMobile;
	}

	public void setFamilyMobile(String familyMobile) {
		this.familyMobile = familyMobile;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getModifytime() {
		return modifytime;
	}

	public void setModifytime(long modifytime) {
		this.modifytime = modifytime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(String isOrg) {
		this.isOrg = isOrg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getIsSameDevice() {
		return isSameDevice;
	}

	public void setIsSameDevice(int isSameDevice) {
		this.isSameDevice = isSameDevice;
	}
	
	

	public String getDeparts() {
		return departs;
	}

	public void setDeparts(String departs) {
		this.departs = departs;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getValidDistance() {
		return validDistance;
	}

	public void setValidDistance(String validDistance) {
		this.validDistance = validDistance;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
}
