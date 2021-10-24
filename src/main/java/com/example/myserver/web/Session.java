package com.example.myserver.web;

import com.example.myserver.services.Loginable;

public class Session {
	private Loginable service;
	private long lastAccessed;
	public Session(Loginable service, long lastAccessed) {
		super();
		this.service = service;
		this.lastAccessed = lastAccessed;
	}
	public Loginable getService() {
		return service;
	}
	public void setService(Loginable service) {
		this.service = service;
	}
	public long getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
	
}
