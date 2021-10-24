package com.example.myserver.utils;

import com.example.myserver.services.CompanyService;
import com.example.myserver.web.Session;

public class SessionUtils {
    public static void validateisSessionExpiration(Session session) throws Exception {

        if (session != null && session.getService() instanceof CompanyService) {
            if (System.currentTimeMillis() - session.getLastAccessed() > 1000 * 60 * 15) {
                throw new Exception("Session Expired");
            }
        }
        session.setLastAccessed(System.currentTimeMillis());
    }
}
