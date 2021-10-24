package com.example.myserver.web;

import com.example.myserver.beans.CategoryType;
import com.example.myserver.beans.Coupon;
import com.example.myserver.exceptions.CouponAllreadyBeenPurchasedException;
import com.example.myserver.exceptions.CouponNotfoundException;
import com.example.myserver.exceptions.CustomerNotFoundException;
import com.example.myserver.services.CustomerService;
import com.example.myserver.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private LoginController loginController;

    @Autowired
    public CustomerController(LoginController loginController) {
        this.loginController = loginController;
    }

    @GetMapping("/coupons")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CustomerService) session.getService()).getAllCustomerCoupons());
    }

    @GetMapping("/getAllCoupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CustomerService) session.getService()).getAllCoupons());
    }

    @PostMapping("/purchaseCoupon")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader String token, @RequestBody Coupon coupon)
			throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((CustomerService) session.getService()).purchaseCoupon(coupon);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/deleteCouponPurchase/{id}")
    public ResponseEntity<?> deleteCouponPurchase(@RequestHeader String token, @PathVariable("id") long couponId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((CustomerService) session.getService()).deleteCouponPurchase(couponId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getCouponsByCategory/{category}")
    public ResponseEntity<?> getCouponsByCategory(@RequestHeader String token,
                                                  @PathVariable("category") CategoryType type) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CustomerService) session.getService()).getCouponsByCategory(type));
    }

    @GetMapping("/getCouponsUpToPrice/{price}")
    public ResponseEntity<?> getCouponsUpToPrice(@RequestHeader String token, @PathVariable double price)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CustomerService) session.getService()).getCouponsUpToPrice(price));
    }

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CustomerService) session.getService()).getCustomerDetails());
    }
}
