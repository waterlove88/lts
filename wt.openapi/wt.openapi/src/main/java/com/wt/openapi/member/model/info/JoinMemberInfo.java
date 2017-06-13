package com.wt.openapi.member.model.info;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class JoinMemberInfo {
    @Override
	public String toString() {
		return "JoinMemberInfo [sex=" + sex + ", userId=" + userId + ", password=" + password + ", phone=" + phone
				+ ", name=" + name + ", nickName=" + nickName + ", bitrh_year=" + birth_year + ", birth_month=" + birth_month
				+ ", birth_day=" + birth_day + ", weight=" + weight + ", height=" + height + "]";
	}
	@Size(min=1, max=1)
    private String sex;
    
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$")
    private String userId;
    
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[!@#$%^*=-])(?=.*[0-9]).{8,}$")
    private String password;
    
    private String phone;
    
    private String name;
    
    private String nickName;
    
    private String birth_year;
    private String birth_month;
    private String birth_day;
    
    private String birth;
    
    private String weight;
    
    private String height;
    
    public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
    
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}