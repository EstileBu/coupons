package com.example.myserver.beans;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long couponId;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "company_id")
	private Company company;

	@Enumerated(EnumType.ORDINAL)
	@JoinTable(name = "catgories", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	@JoinColumn(table = "categories", name = "name")
	private CategoryType type;

	@Column
	private String title;

	@Column
	private String description;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column
	private int amount;

	@Column
	private double price;

	@Column
	private String image;

	public Coupon() {
	}

	public Coupon(Coupon c) {
		this(c.type, c.title, c.description, c.startDate, c.endDate, c.amount, c.price, c.image);
	}

	public Coupon(CategoryType type, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		super();
		this.type = type;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", company=" + company + ", type=" + type + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCouponId() {
		return couponId;
	}
}
