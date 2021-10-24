package com.example.myserver.web;

import com.example.myserver.services.CompanyService;
import com.example.myserver.services.CustomerService;
import com.example.myserver.services.Loginable;
import com.example.myserver.services.ManagerService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("login")
public class LoginController {

	private Map<String, Session> sessions;
	private ConfigurableApplicationContext ctx;

	public LoginController(ConfigurableApplicationContext ctx) {
		sessions = new HashMap<>();
		this.ctx = ctx;
	}

	@PostMapping("{email}")
	public ResponseEntity<String> login(@PathVariable String email, @RequestParam("password") String password,
			@RequestParam("type") String type) {
		if (type.equals("manager")) {
			ManagerService service = ctx.getBean(ManagerService.class);
			if (service.login(email, password)) {
				return ResponseEntity.ok(addSession(service));
			}
		} else {
			CustomerService service = ctx.getBean(CustomerService.class);
			if (service.login(email, password)) {
				return ResponseEntity.ok(addSession(service));
			} else {
				CompanyService companyService = ctx.getBean(CompanyService.class);
				if (companyService.login(email, password)) {
					String token = UUID.randomUUID().toString();
					return ResponseEntity.ok(addSession(companyService));
				}
			}

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Login!");

	}

	private String addSession(Loginable service) {
		String token = UUID.randomUUID().toString();
		sessions.put(token, new Session(service, System.currentTimeMillis()));
		return token;

	}

	public Map<String, Session> getSessions() {
		return sessions;
	}
	
	
}
