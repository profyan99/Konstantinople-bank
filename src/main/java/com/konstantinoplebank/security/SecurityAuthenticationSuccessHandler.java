package com.konstantinoplebank.security;

import com.konstantinoplebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Is called when the user has been authorized successfully
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Component
public class SecurityAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final
    UserService service;

    @Autowired
    public SecurityAuthenticationSuccessHandler(UserService service) {
        this.service = service;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = httpServletResponse.getWriter();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long id = service.getByName(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDetails.getUsername()))
                .getId();
        writer.println(id);
        writer.flush();

    }
}
