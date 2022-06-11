package dev.innov8.prism.common.security;

import dev.innov8.prism.auth.AuthService;
import dev.innov8.prism.auth.TokenService;
import dev.innov8.prism.auth.Principal;
import dev.innov8.prism.common.exceptions.AuthenticationException;
import dev.innov8.prism.common.exceptions.InvalidTokenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {

    private final TokenService tokenService;
    private final AuthService authService;
    private SecurityContext securityContext;

    @Autowired
    public SecurityAspect(TokenService tokenService, AuthService authService) {
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @Autowired
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Before("@annotation(dev.innov8.prism.common.security.Authenticated)")
    public void requireAuthentication() {
        if (!sessionExists()) throw new AuthenticationException("No session token found.");
        Principal requester = getTokenHolderDetails();
        try {
            authService.authenticate(requester.getOrgId(), requester.getAuthCode());
        } catch (Exception e) {
            throw new InvalidTokenException("The provided token contained invalid organization details.");
        }
        securityContext.setRequester(requester);
    }

    private boolean sessionExists() {
        return tokenService.isTokenValid(getCurrentRequest().getHeader("Authorization"));
    }

    private Principal getTokenHolderDetails() {
        return tokenService.extractTokenDetails(getCurrentRequest().getHeader("Authorization"));
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
