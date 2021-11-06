package com.example.myserver.services;

import com.example.myserver.beans.CategoryType;
import com.example.myserver.beans.Coupon;
import com.example.myserver.beans.Customer;
import com.example.myserver.exceptions.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Scope("prototype")
@Service
public class CustomerService extends ClientService implements Loginable {

	public long customerId;

	@Override
	public boolean login(String email, String password) {
		Optional<Customer> cust = custRepo.findByEmailAndPassword(email, password);
		if (cust.isPresent()) {
			customerId = cust.get().getId();
			return true;
		} else {
			return false;
		}
	}

	// ===================== purchase coupon =====================\\
	public void purchaseCoupon(Coupon coupon) throws Exception {
		Calendar cal = Calendar.getInstance();
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		Coupon c = coupRepo.findById(coupon.getCouponId()).orElseThrow(CouponNotfoundException::new);
		for (Coupon coup : cust.getCoupons()) {
			if (coup.equals(coupon)) {
				throw new CouponAllreadyBeenPurchasedException();
			}
		}
		if (coupon.getAmount() > 0 && coupon.getEndDate().after(new Date(cal.getTimeInMillis()))) {
			cust.getCoupons().add(coupon);
			c.setAmount(coupon.getAmount() - 1);
			coupRepo.save(coupon);
		}
	}

	// ===================== delete coupon purchase =================\\

	public void deleteCouponPurchase(long couponId) throws Exception {
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		custRepo.deletePurchaseById(couponId, customerId);
	}

	// ====================== get all customer coupons ==================\\

	public List<Coupon> getAllCustomerCoupons() throws Exception {
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coup : cust.getCoupons()) {
			if (coup.getCompany() != null)
				coup.getCompany().setCoupons(null);
			coupons.add(coup);
		}
		if (coupons.size() == 0) {
			throw new NoCustomerCouponsException();
		} else
			return coupons;
	}
	// ======================= get all coupons =====================\\

	public List<Coupon> getAllCoupons() throws Exception {
		List<Coupon> all = coupRepo.findAll();
		all.forEach(coupon -> {
			if (coupon.getCompany() != null)
				coupon.getCompany().setCoupons(null);
		});
		return all;
	}

	// ======================= get coupons by =========================\\

	public List<Coupon> getCouponsByCategory(CategoryType type)
			throws Exception {
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coup : cust.getCoupons()) {
			if (coup.getCompany() != null)
				coup.getCompany().setCoupons(null);
			if (coup.getType().equals(type))
				coupons.add(coup);
		}
		if ((coupons.size()) == 0) {
			throw new NoCouponsByCategoryException();
		} else
			return coupons;
	}

	public List<Coupon> getCouponsUpToPrice(double price)
			throws Exception {
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coup : cust.getCoupons()) {
			if(coup.getCompany() != null ) coup.getCompany().setCoupons(null);
			if (coup.getPrice() <= (price))
				coupons.add(coup);
		}
		if ((coupons.size()) == 0) {
			throw new NoCouponsUpToPriceException();
		} else
			return coupons;
	}

	// ========================== get customer details ==================\\

	public Customer getCustomerDetails() throws Exception {
		Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		return cust;
	}

}
