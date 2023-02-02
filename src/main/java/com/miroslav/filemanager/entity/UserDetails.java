package com.miroslav.filemanager.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "gender")
	private int gender;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "account_status")
	private String accountStatus;

	public UserDetails(String avatar, int gender, Date dob, String phone, String address, String accountStatus) {
		super();
		this.avatar = avatar;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.accountStatus = accountStatus;
	}

	public UserDetails() {
		super();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", avatar=" + avatar + ", gender=" + gender + ", dob=" + dob + ", phone=" + phone
				+ ", address=" + address + "]";
	}
}
