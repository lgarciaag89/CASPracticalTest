package com.casystems.caspracticaltest.security;
import com.casystems.caspracticaltest.system.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimeOutAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        httpServletRequest.getSession(false).setMaxInactiveInterval(userDetail.getTimeout());

        UserDetail user = new UserDetail(userDetail.getUsername(),"",userDetail.getAuthorities(),userDetail.getTimeout());
        httpServletRequest.getSession(false).setAttribute("loggedInUser", user);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/menu");
    }
}
