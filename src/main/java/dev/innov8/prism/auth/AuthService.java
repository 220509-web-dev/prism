package dev.innov8.prism.auth;

import dev.innov8.prism.auth.dtos.AuthCodeResponse;
import dev.innov8.prism.auth.dtos.ResetAuthCodeRequest;
import dev.innov8.prism.common.exceptions.AuthenticationException;
import dev.innov8.prism.common.exceptions.AuthorizationException;
import dev.innov8.prism.common.exceptions.ResourceNotFoundException;
import dev.innov8.prism.organization.OrgRepository;
import dev.innov8.prism.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService {

    private final OrgRepository orgRepo;

    @Autowired
    public AuthService(OrgRepository orgRepository) {
        this.orgRepo = orgRepository;
    }

    public Organization authenticate(String orgId, String authCode) {
        return orgRepo.findOrganizationByIdAndAuthCode(orgId, authCode).orElseThrow(AuthenticationException::new);
    }

    @Transactional
    public AuthCodeResponse resetOrgAuthCode(ResetAuthCodeRequest request) {
        Organization org = orgRepo.findById(request.getOrgId()).orElseThrow(ResourceNotFoundException::new);
        if (org.getKey().equals(request.getOrgKey())) {
            String newAuthCode = UUID.randomUUID().toString();
            org.setAuthCode(newAuthCode);
            return new AuthCodeResponse(org.getId(), newAuthCode);
        } else {
            throw new AuthorizationException("Incorrect organization key provided.");
        }
    }

}
