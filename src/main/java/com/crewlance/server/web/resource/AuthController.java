package com.crewlance.server.web.resource;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.crewlance.server.error.SystemException;
import com.crewlance.server.security.TokenAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private final AuthenticationController authenticationController;
    private final String domain;

    public AuthController(AuthenticationController authenticationController, @Value("${com.auth0.domain}") String domain) {
        this.authenticationController = authenticationController;
        this.domain = domain;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(HttpServletRequest request) {
        String redirectUri = String.format("%s://%s:%s/callback", request.getScheme(), request.getServerName(), request.getServerPort());
        String authorizeUrl = authenticationController.buildAuthorizeUrl(request, redirectUri)
                .withAudience(String.format("https://%s/userinfo", domain))
                .build();
        return String.format("redirect:%s", authorizeUrl);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected static void logout(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(HttpServletRequest request) {
        postCallback(request);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private void postCallback(HttpServletRequest request) {
        try {
            Tokens tokens = authenticationController.handle(request);
            TokenAuthentication tokenAuth = new TokenAuthentication(JWT.decode(tokens.getIdToken()));
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
        } catch (AuthenticationException | IdentityVerificationException e) {
            SecurityContextHolder.clearContext();
            throw new SystemException(e.getMessage(), e);
        }
    }
}
