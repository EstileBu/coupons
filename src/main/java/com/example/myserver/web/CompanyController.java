package com.example.myserver.web;

import com.example.myserver.beans.CategoryType;
import com.example.myserver.beans.Coupon;
import com.example.myserver.exceptions.CompanyNotFoundException;
import com.example.myserver.services.CompanyService;
import com.example.myserver.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
public class CompanyController {

    private LoginController loginController;

    @Autowired
    public CompanyController(LoginController loginController) {
        this.loginController = loginController;
    }

    @GetMapping("/getAllCompanyCoupons")
    public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader String token)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CompanyService) session.getService()).getAllCompanyCoupons());
    }

    @PostMapping("/coupon}")
    public ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((CompanyService) session.getService()).addCoupon(coupon);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/coupons")
    public ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((CompanyService) session.getService()).updateCoupon(coupon);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader String token, @PathVariable("id") long couponId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((CompanyService) session.getService()).deleteCoupon(couponId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getCouponByCategory/{token}/{catrgory}")
    public ResponseEntity<?> getCouponByCategory(@RequestHeader String token,
                                                 @PathVariable("category") CategoryType type) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CompanyService) session.getService()).getCouponByCategory(type));
    }

    @GetMapping("/getCouponUpToPrice/{price}")
    public ResponseEntity<?> getCouponUpToPrice(@RequestHeader String token, @PathVariable double price)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CompanyService) session.getService()).getCouponUpToPrice(price));
    }

    @GetMapping("/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((CompanyService) session.getService()).getCompanyDetails());
    }
}
