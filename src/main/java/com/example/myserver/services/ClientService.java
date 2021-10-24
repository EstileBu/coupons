package com.example.myserver.services;

import com.example.myserver.database.CompanyRepository;
import com.example.myserver.database.CouponRepository;
import com.example.myserver.database.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
	@Autowired
	protected CouponRepository coupRepo;

	@Autowired
	protected CompanyRepository compRepo;

	@Autowired
	protected CustomerRepository custRepo;
	
	public ClientService() {}
	
	public abstract boolean login(String email, String password);
}
