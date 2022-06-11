package dev.innov8.prism.organization;

import dev.innov8.prism.common.exceptions.AuthorizationException;
import dev.innov8.prism.common.exceptions.ResourceNotFoundException;
import dev.innov8.prism.common.exceptions.ResourcePersistenceException;
import dev.innov8.prism.organization.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrgService {

    private final OrgRepository orgRepo;

    @Autowired
    public OrgService(OrgRepository orgRepo) {
        this.orgRepo = orgRepo;
    }

    @Transactional
    public NewOrgResponse createNewOrganization(NewOrgRequest request) {
        Organization newOrg = request.extractResource();
        newOrg.setId(UUID.randomUUID().toString());
        newOrg.setAuthCode(UUID.randomUUID().toString());

        try {
            orgRepo.save(newOrg);
        } catch (Exception e) {
            throw new ResourcePersistenceException(e.getMessage());
        }

        return new NewOrgResponse(newOrg.getId(), newOrg.getAuthCode());
    }

    @Transactional
    public void editOrganization(EditOrgRequest request, String authCode) {
        Organization org = orgRepo.findById(request.getOrgId()).orElseThrow(ResourceNotFoundException::new);
        if (org.getAuthCode().equals(authCode)) {
            org.setName(request.getOrgName());
        } else {
            throw new AuthorizationException("Incorrect authorization code provided.");
        }

    }

    @Transactional
    public void removeOrganization(String orgId, String authCode) {
        Organization org = orgRepo.findById(orgId).orElseThrow(ResourceNotFoundException::new);
        if (org.getAuthCode().equals(authCode)) {
            try {
                orgRepo.delete(org);
            } catch (Exception e) {
                throw new ResourcePersistenceException(e.getMessage());
            }
        } else {
            throw new AuthorizationException("Incorrect authorization code provided.");
        }
    }

}
