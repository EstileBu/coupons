package com.example.myserver.database;

import com.example.myserver.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmailAndPassword(String email, String password);

	Optional<Customer> findByEmail(String email);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from customers_vs_coupons where customer_id=:customerId and coupon_id=:couponId")
	void deletePurchaseById(@Param("couponId") long couponId, @Param("customerId") long customerId);
}
