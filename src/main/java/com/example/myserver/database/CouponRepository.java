package com.example.myserver.database;


import com.example.myserver.beans.CategoryType;
import com.example.myserver.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CouponRepository extends JpaRepository<Coupon, Long>{

    //get all coupons by company id
    @Query("select new com.example.myserver.beans.Coupon(c) from Coupon c where c.company.id= :companyId")
    public List<Coupon> getAllCouponsByCompanyId(@Param("companyId") long companyId);

    //get all coupons by type
    @Query("select new com.example.myserver.beans.Coupon(c) from Coupon c where c.type= :type")
    public List<Coupon> getAllCouponsByType(@Param("type") CategoryType type);

}
