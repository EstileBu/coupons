package com.example.myserver.beans;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	
	@OneToMany(cascade = CascadeType.REMOVE)

//	@JoinTable(joinColumns = @JoinColumn(name = "id",  referencedColumnName = "company_id", table = "coupons"))
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Set<Coupon> coupons;

	public Company() {
	}

	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public long getId() {
		return id;
	}

}
