package dev.innov8.prism.auth;

import dev.innov8.prism.auth.dtos.AuthCodeResponse;
import dev.innov8.prism.auth.dtos.AuthenticationRequest;
import dev.innov8.prism.auth.dtos.ResetAuthCodeRequest;
import dev.innov8.prism.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Principal authenticate(@RequestBody AuthenticationRequest authRequest, HttpServletResponse resp) {
        Organization org = authService.authenticate(authRequest.getOrgId(), authRequest.getAuthCode());
        Principal payload = new Principal(org);
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return payload;
    }

    @PatchMapping(value = "/code", consumes = "application/json", produces = "application/json")
    public AuthCodeResponse requestNewAuthCode(@Valid @RequestBody ResetAuthCodeRequest resetAuthCodeRequest) {
        return authService.resetOrgAuthCode(resetAuthCodeRequest);
    }

}
