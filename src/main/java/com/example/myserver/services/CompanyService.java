package com.example.myserver.services;

import com.example.myserver.beans.CategoryType;
import com.example.myserver.beans.Company;
import com.example.myserver.beans.Coupon;
import com.example.myserver.beans.Customer;
import com.example.myserver.exceptions.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class CompanyService extends ClientService implements Loginable{
	
	private long companyId;

	@Override
	public boolean login(String email, String password) {
		Optional<Company> comp= compRepo.findByEmailAndPassword(email, password);
		if( comp.isPresent()) {
			companyId=comp.get().getId();
			return true;
		}else
			return false;
	}
	
	
	//=================== add method ======================\\
	
	public void addCoupon(Coupon coupon) throws Exception {
		for (Coupon coup: coupRepo.findAll()) {
			if(coup.getTitle().equals(coupon.getTitle())) {
				throw new CouponExistsException();
			}
		}
		coupRepo.save(coupon);
	}
	
	//=================== update method =====================\\
	
	public void updateCoupon(Coupon coupon) throws Exception{
		for (Coupon coup : coupRepo.findAll()) {
			if(coup.getCouponId() != coupon.getCouponId()
					||coup.getCompany().getId() != coupon.getCompany().getId()
		)
				throw new CannotFindCouponIdOrCompanyIdException();
			else if(!(coup.getTitle().equals(coupon.getTitle())))
					coupRepo.save(coupon);
			
			else
				throw new CouponExistsException();
		}
	}
	//====================== delete method ===================\\
	
	public void deleteCoupon(long couponId ) throws Exception{
		Company comp=compRepo.findById(companyId).orElseThrow(CompanyNotFoundException:: new);
		Coupon coup=coupRepo.findById(couponId).orElseThrow(CouponNotfoundException:: new);
		for (Customer cust : custRepo.findAll()) {
			for (Coupon c: cust.getCoupons()) {
				if(c.getCouponId()==couponId
						&&c.getCompany().getId()==comp.getId()
						)
					coupRepo.deleteById(couponId);
					comp.getCoupons().remove(couponId);
				
			}
		}
		}
	
	//==================== get all method ======================\\
	public List<Coupon>getAllCompanyCoupons()throws Exception{
		System.out.println(companyId);
		Company comp=compRepo.findById(companyId).orElseThrow(CompanyNotFoundException:: new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coupon: comp.getCoupons()) {
			coupons.add(coupon);
		}
		if(!(coupons.size()>0))
			throw new NoCouponsException();
		return coupons;
			
			

	}
	
	//===================== get by methods =====================\\
	
	public List<Coupon> getCouponByCategory(CategoryType type)throws Exception{
		Company comp=compRepo.findById(companyId).orElseThrow(CompanyNotFoundException:: new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coup: comp.getCoupons()) {
			if(coup.getType().equals(type)) {
				coupons.add(coup);
			}
		}if(!(coupons.size()>0))
			throw new NoCouponsByCategoryException();
		return coupons;
	}
 
	
	public List<Coupon> getCouponUpToPrice(double price)throws Exception{
		Company comp=compRepo.findById(companyId).orElseThrow(CompanyNotFoundException:: new);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coup: comp.getCoupons()) {
			if(coup.getPrice()==(price)) {
				coupons.add(coup);
			}
		}if(!(coupons.size()>0)) {
			throw new NoCouponsUpToPriceException();
		}
		return coupons;
	
	
	}
	//==================== get company details ====================\\
	
	public Company getCompanyDetails() throws Exception {
		Company comp=compRepo.findById(companyId).orElseThrow(CompanyNotFoundException:: new);
		return comp;
		
	}
	}
