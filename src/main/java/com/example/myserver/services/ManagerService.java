package com.example.myserver.services;

import com.example.myserver.beans.Company;
import com.example.myserver.beans.Coupon;
import com.example.myserver.beans.Customer;
import com.example.myserver.exceptions.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Scope("prototype")
public class ManagerService extends ClientService implements Loginable {

    @Override
    public boolean login(String email, String password) {
        if (email.contentEquals("admin@admin.com") && password.equals("admin")) {
            return true;
        }

        return false;
    }

    //================ add methods ==========================\\

    public void addCompany(Company company) throws CompanyExsistsException {
        Optional<Company> comp = compRepo.findByEmailAndName(company.getEmail(), company.getName());
        if (comp.isPresent()) {
            throw new CompanyExsistsException();
        }
        compRepo.save(company);
    }


    public void addCustomer(Customer customer) throws CustomerExsistsException {
        Optional<Customer> cust = custRepo.findByEmail(customer.getEmail());
        if (cust.isPresent()) {
            throw new CustomerExsistsException();
        }
        custRepo.save(customer);
    }

    //=================== updating  methods ==================\\
    public void updateCompany(Company company) throws Exception {
        if (!compRepo.existsById(company.getId())) {
            throw new CompanyNotFoundException();
        }
        compRepo.save(company);
    }


    public void updateCustomer(Customer customer) throws Exception {
         if (!custRepo.existsById(customer.getId())) {
            throw new CustomerNotFoundException();
        }
        custRepo.save(company);
    }


    //===================== delete methods ===================\\

    public void deleteCompany(long companyId) throws Exception {
        Company comp = compRepo.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        for (Customer cust : custRepo.findAll()) {
            for (Coupon coup : cust.getCoupons()) {
                if (coup.getCompany().getId() == comp.getId()) {
                    coupRepo.delete(coup);
                }
            }
        }
        comp.getCoupons().clear();
        compRepo.deleteById(companyId);
    }

    public void deleteCustomer(long customerId) throws Exception {
        Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        cust.getCoupons().clear();
        custRepo.deleteById(customerId);
    }

    //======================== get all methods ================\\

    public List<Company> getAllCompanies() throws Exception {
        if (compRepo.findAll().size() > 0) {
            return compRepo.findAll();
        } else

            throw new NoCompaniesException();

    }

    public List<Customer> getAllCustomers() throws Exception {
        if (custRepo.findAll().size() > 0) {
            return custRepo.findAll();
        } else

            throw new NoCustomersException();

    }
    //============== get one methods =================\\

    public Company getOneCompany(long companyId) throws Exception {
        Company comp = compRepo.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        return comp;

    }

    public Customer getOneCustomer(long customerId) throws Exception {
        Customer cust = custRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        return cust;
    }

}

