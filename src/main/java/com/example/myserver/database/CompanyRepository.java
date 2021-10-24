package com.example.myserver.database;

import com.example.myserver.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> findByEmailAndPassword(String email, String password);

	Optional<Company> findByEmailAndName(String email, String name);

}
