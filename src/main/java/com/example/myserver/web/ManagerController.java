package com.example.myserver.web;

import com.example.myserver.beans.Company;
import com.example.myserver.beans.Customer;
import com.example.myserver.exceptions.*;
import com.example.myserver.services.ManagerService;
import com.example.myserver.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
public class ManagerController {

    private LoginController loginController;

    @Autowired
    public ManagerController(LoginController loginController) {
        this.loginController = loginController;
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((ManagerService) session.getService()).getAllCompanies());
    }

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).addCompany(company);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).addCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@RequestHeader String token, @PathVariable("id") long companyId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).deleteCompany(companyId);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader String token, @PathVariable("id") long customerId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).deleteCustomer(customerId);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/updateCompany")
    public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).updateCompany(company);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        ((ManagerService) session.getService()).updateCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader String token) throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((ManagerService) session.getService()).getAllCustomers());
    }

    @GetMapping("/getCompany/{id}")
    public ResponseEntity<?> getOneCompany(@RequestHeader String token, @PathVariable("id") long companyId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((ManagerService) session.getService()).getOneCompany(companyId));
    }

    @GetMapping("/getOneCustomer/{id}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader String token, @PathVariable("id") long customerId)
            throws Exception {
        Session session = loginController.getSessions().get(token);
        SessionUtils.validateisSessionExpiration(session);
        return ResponseEntity.ok(((ManagerService) session.getService()).getOneCustomer(customerId));
    }
}
