package com.TM.LTE.bean;

public class Member {
	private String m_id;
	private String m_pw;
	private int m_birth;
	private int m_blacklist;
	private String m_email;
	private String m_phone;
	private String m_name;  //이름or상호명
	private String m_bnum; //사업자번호or회원번호
	private String m_gender; //성별
	private String m_part;  //구분
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public int getM_birth() {
		return m_birth;
	}
	public void setM_birth(int m_birth) {
		this.m_birth = m_birth;
	}
	public int getBlacklist() {
		return m_blacklist;
	}
	public void setBlacklist(int blacklist) {
		this.m_blacklist = blacklist;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_bnum() {
		return m_bnum;
	}
	public void setM_bnum(String m_bnum) {
		this.m_bnum = m_bnum;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public String getM_part() {
		return m_part;
	}
	public void setM_part(String m_part) {
		this.m_part = m_part;
	}
}
