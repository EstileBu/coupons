package com.example.myserver.beans;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column
	private String email;

	@Column
	private String password;

	@JoinTable(name="customers_vs_coupons",
     joinColumns= @JoinColumn(name="customer_id" , referencedColumnName="id"),
     inverseJoinColumns=@JoinColumn(name="coupon_id", referencedColumnName="id"))
    @ManyToMany
	private Set<Coupon> coupons;

	public Customer() {
	}

	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
